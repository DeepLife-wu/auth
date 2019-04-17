/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.freemark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoyao.auth.authorize.annotation.SysLog;
import com.guoyao.auth.authorize.exception.DwzDataException;
import com.guoyao.auth.authorize.model.Permission;
import com.guoyao.auth.authorize.web.consts.AppConstants;
import com.guoyao.auth.authorize.web.consts.ServerCode;
import com.guoyao.auth.authorize.web.controller.AbstractController;
import com.guoyao.auth.authorize.web.controller.converter.PermissionConverter;
import com.guoyao.auth.authorize.web.controller.query.PermissionQueryCondition;
import com.guoyao.auth.authorize.web.dwz.CallbackType;
import com.guoyao.auth.authorize.web.dwz.DWZResponse;
import com.guoyao.auth.authorize.web.dwz.DwzCode;
import com.guoyao.auth.authorize.web.form.PermissionForm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:27:53】
 */
@Slf4j
@Controller
@RequestMapping("/dwz/permission")
public class PermissionController extends AbstractController {
	
	@RequestMapping("/list")
    public String list(String tabId,PermissionQueryCondition condition, 
    		@RequestParam(value="pageNum",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_PAGE) Integer pageNum,
            @RequestParam(value="numPerPage",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_SIZE) Integer numPerPage,
    		Model model)
    {
		if(StringUtils.isNotBlank(tabId)) super.tabId = tabId;
        List<Order> orders=new ArrayList<Sort.Order>();
        orders.add(new Order(Direction.DESC, AppConstants.AUTHORIZE_CONTROLLER_SORT));
        Pageable pageable = new PageRequest(pageNum - 1,numPerPage,new Sort(orders));
		Page<PermissionForm> page = permissionService.findByCondition(condition,pageable);
		
		model.addAttribute("condition",condition);
		model.addAttribute("page", page);
        return "/dwz/permission/list";
    }
	
	@GetMapping("/add")
	public String getAdd() {
		return "/dwz/permission/add";
	}
	
	@SysLog("添加权限")
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public DWZResponse postAdd(@Valid PermissionForm permissionForm,BindingResult errors) throws IOException {
		Permission permission = PermissionConverter.form2EntityCreate(permissionForm);
		permissionService.save(permission);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}

	@GetMapping("/update/{pid}")
	public String getUpdate(@PathVariable Long pid,Model model) {
		Permission permission = permissionService.findOne(pid);
		if(permission == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		PermissionForm permissionForm = PermissionConverter.entity2FormCreate(permission);
		model.addAttribute("permissionForm", permissionForm);
		return "/dwz/permission/update";
	}
	
	@SysLog("更新权限")
	@PostMapping("/update/{pid}")
	@ResponseBody
	public DWZResponse postUpdate(@PathVariable Long pid,@Valid PermissionForm permissionForm,BindingResult errors,Model model) {
		Permission permission = permissionService.findOne(pid);
		if(permission == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		PermissionConverter.form2EntityUpdate(permissionForm, permission);
		permissionService.save(permission);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}
	
	@SysLog("删除权限")
	@PostMapping("/delete/{pid}")
	@ResponseBody
	public DWZResponse delete(@PathVariable Long pid) {
		permissionService.delete(pid);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.forward).forwardUrl("dwz/permission/list").build();
	}
}
