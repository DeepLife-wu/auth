/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.freemark;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoyao.auth.authorize.annotation.SysLog;
import com.guoyao.auth.authorize.exception.DwzDataException;
import com.guoyao.auth.authorize.model.Permission;
import com.guoyao.auth.authorize.model.Role;
import com.guoyao.auth.authorize.web.consts.AppConstants;
import com.guoyao.auth.authorize.web.consts.ServerCode;
import com.guoyao.auth.authorize.web.controller.AbstractController;
import com.guoyao.auth.authorize.web.controller.converter.RoleConverter;
import com.guoyao.auth.authorize.web.controller.query.RoleQueryCondition;
import com.guoyao.auth.authorize.web.dwz.CallbackType;
import com.guoyao.auth.authorize.web.dwz.DWZResponse;
import com.guoyao.auth.authorize.web.dwz.DwzCode;
import com.guoyao.auth.authorize.web.form.RoleForm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:27:53】
 */
@Slf4j
@Controller
@RequestMapping("/dwz/role")
public class RoleController extends AbstractController {
	
	@RequestMapping("/list")
	public String list(String tabId,RoleQueryCondition condition, 
			@RequestParam(value="pageNum",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_PAGE) Integer pageNum,
            @RequestParam(value="numPerPage",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_SIZE) Integer numPerPage,
    		Model model) {
		if(StringUtils.isNotBlank(tabId)) super.tabId = tabId;
        List<Order> orders=new ArrayList<Sort.Order>();
        orders.add(new Order(Direction.DESC, AppConstants.AUTHORIZE_CONTROLLER_SORT));
        Pageable pageable = new PageRequest(pageNum - 1,numPerPage,new Sort(orders));
		Page<RoleForm> page = roleService.findByCondition(condition,pageable);
		
		model.addAttribute("condition",condition);
		model.addAttribute("page", page);
		return "/dwz/role/list";
	}
	
	@GetMapping("/add")
	public String getAdd() {
		return "/dwz/role/add";
	}
	
	@SysLog("添加角色")
	@PostMapping("/add")
	@ResponseBody
	public DWZResponse postAdd(@Valid RoleForm roleForm,BindingResult errors) {
		Role role = RoleConverter.form2EntityCreate(roleForm);
		roleService.save(role);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
							 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}
	
	@GetMapping("/update/{rid}")
	public String getUpdate(@PathVariable Long rid,Model model) {
		Role role = roleService.findOne(rid);
		if(role == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		RoleForm roleForm = RoleConverter.entity2FormCreate(role);
		model.addAttribute("roleForm", roleForm);
		return "/dwz/role/update";
	}
	
	@SysLog("更新角色")
	@PostMapping("/update/{rid}")
	@ResponseBody
	public DWZResponse postUpdate(@PathVariable Long rid,@Valid RoleForm roleForm,BindingResult errors,Model model) {
		Role role = roleService.findOne(rid);
		if(role == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		RoleConverter.form2EntityUpdate(roleForm,role);
		roleService.save(role);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}
	
	@SysLog("删除角色")
	@PostMapping("/delete/{rid}")
	@ResponseBody
	public DWZResponse delete(@PathVariable Long rid) {
		roleService.delete(rid);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.forward).forwardUrl("dwz/role/list").build();
	}
	
	@GetMapping("/grant/{rid}")
	public String getGrant(@PathVariable Long rid,Model model) {
		Role role = roleService.findOne(rid);
		if(role == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		
		Set<Permission> hasPermission = role.getPermissions();
		List<Permission> allPermissions = permissionService.findAll();
		
		if(CollectionUtils.isNotEmpty(hasPermission)) {
			for(Permission p : hasPermission) {
				if(allPermissions.contains(p)) {
					allPermissions.remove(p);
				}
			}
		}
		
		RoleForm roleForm = RoleConverter.entity2FormCreate(role);
		model.addAttribute("roleForm", roleForm);
		model.addAttribute("hasPermission", hasPermission);
		model.addAttribute("unPermList", allPermissions);
		
		return "/dwz/role/grant";
	}
	
	@SysLog("授权角色")
	@PostMapping("/grant/{rid}")
	@ResponseBody
	public DWZResponse postGrant(@PathVariable Long rid,@RequestParam(value="hasPermission",required=false) List<String> hasPermission) {
		Role role = roleService.findOne(rid);
		if(role == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		
		role.getPermissions().clear();
		if(CollectionUtils.isNotEmpty(hasPermission)) {
			for(String pid : hasPermission) {
				if(NumberUtils.isNumber(pid)) {
					Permission perm = permissionService.findOne(NumberUtils.toLong(pid));
					role.getPermissions().add(perm);
				}
			}
		}
		roleService.save(role);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}
}
