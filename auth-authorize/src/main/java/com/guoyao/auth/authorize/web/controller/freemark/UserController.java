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
import com.guoyao.auth.authorize.model.Role;
import com.guoyao.auth.authorize.model.User;
import com.guoyao.auth.authorize.web.consts.AppConstants;
import com.guoyao.auth.authorize.web.consts.ServerCode;
import com.guoyao.auth.authorize.web.controller.AbstractController;
import com.guoyao.auth.authorize.web.controller.converter.UserConverter;
import com.guoyao.auth.authorize.web.controller.query.UserQueryCondition;
import com.guoyao.auth.authorize.web.dwz.CallbackType;
import com.guoyao.auth.authorize.web.dwz.DWZResponse;
import com.guoyao.auth.authorize.web.dwz.DwzCode;
import com.guoyao.auth.authorize.web.form.UserForm;
import com.guoyao.auth.authorize.web.form.UserFormUpdate;
import com.guoyao.auth.authorize.web.form.UserPasswordForm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:27:53】
 */
@Slf4j
@Controller
@RequestMapping("/dwz/user")
public class UserController extends AbstractController {

	@RequestMapping("/list")
	public String list(String tabId,UserQueryCondition condition, 
			@RequestParam(value="pageNum",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_PAGE) Integer pageNum,
            @RequestParam(value="numPerPage",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_SIZE) Integer numPerPage,
    		Model model) {
		if(StringUtils.isNotBlank(tabId)) super.tabId = tabId;
        List<Order> orders=new ArrayList<Sort.Order>();
        orders.add(new Order(Direction.DESC, AppConstants.AUTHORIZE_CONTROLLER_SORT));
        Pageable pageable = new PageRequest(pageNum - 1,numPerPage,new Sort(orders));
		Page<UserForm> page = userService.findByCondition(condition,pageable);
		
		model.addAttribute("condition",condition);
		model.addAttribute("page", page);
		return "/dwz/user/list";
	}
	
	@GetMapping("/add")
	public String getAdd() {
		return "/dwz/user/add";
	}
	
	@SysLog("添加用户")
	@PostMapping("/add")
	@ResponseBody
	public DWZResponse postAdd(@Valid UserForm userForm,BindingResult errors) {
		User user = UserConverter.form2EntityCreate(userForm);
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		userService.save(user);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
							 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}
	
	@GetMapping("/update/{uid}")
	public String getUpdate(@PathVariable Long uid,Model model) {
		User user = userService.findOne(uid);
		if(user == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		UserForm userForm = UserConverter.entity2FormCreate(user);
		model.addAttribute("userForm", userForm);
		return "/dwz/user/update";
	}
	
	@SysLog("修改用户")
	@PostMapping("/update/{uid}")
	@ResponseBody
	public DWZResponse postUpdate(@PathVariable Long uid,@Valid UserFormUpdate userForm,BindingResult errors,Model model) {
		User user = userService.findOne(uid);
		if(user == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		UserConverter.form2EntityUpdate(userForm,user);
		userService.save(user);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}
	
	@SysLog("删除用户")
	@PostMapping("/delete/{uid}")
	@ResponseBody
	public DWZResponse delete(@PathVariable Long uid) {
		userService.delete(uid);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.forward).forwardUrl("dwz/user/list").build();
	}
	
	@GetMapping("/grant/{uid}")
	public String getGrant(@PathVariable Long uid,Model model) {
		User user = userService.findOne(uid);
		if(user == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		
		Set<Role> hasRole = user.getRoles();
		List<Role> allRoles = roleService.findAll();
		
		if(CollectionUtils.isNotEmpty(hasRole)) {
			for(Role r : hasRole) {
				if(allRoles.contains(r)) {
					allRoles.remove(r);
				}
			}
		}
		
		UserForm userForm = UserConverter.entity2FormCreate(user);
		model.addAttribute("userForm", userForm);
		model.addAttribute("hasRole", hasRole);
		model.addAttribute("unRoleList", allRoles);
		return "/dwz/user/grant";
	}
	
	@SysLog("授权用户")
	@PostMapping("/grant/{uid}")
	@ResponseBody
	public DWZResponse postGrant(@PathVariable Long uid,@RequestParam(value="hasRole",required=false) List<String> hasRole) {
		User user = userService.findOne(uid);
		if(user == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		
		user.getRoles().clear();
		if(CollectionUtils.isNotEmpty(hasRole)) {
			for(String rid : hasRole) {
				if(NumberUtils.isNumber(rid)) {
					Role role = roleService.findOne(NumberUtils.toLong(rid));
					user.getRoles().add(role);
				}
			}
		}
		userService.save(user);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}
	
	@GetMapping("/password/{uid}")
	public String getPassword(@PathVariable Long uid,Model model) {
		User user = userService.findOne(uid);
		if(user == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		UserForm userForm = UserConverter.entity2FormCreate(user);
		model.addAttribute("userForm", userForm);
		return "/dwz/user/password";
	}
	
	@SysLog("修改密码")
	@PostMapping("/password/{uid}")
	@ResponseBody
	public DWZResponse postPassword(@PathVariable Long uid,@Valid UserPasswordForm userForm,BindingResult errors,Model model) {
		User user = userService.findOne(uid);
		if(user == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		log.info("用户原始加密密码:{}",user.getPassword());
		String password = passwordEncoder.encode(userForm.getPassword());
		user.setPassword(password);
		log.info("用户加密之后密码:{}",user.getPassword());
		userService.save(user);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}
}
