///**
// * 
// */
//package com.guoyao.auth.authorize.web.controller.restful;
//
//import javax.validation.Valid;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.annotation.JsonView;
//import com.guoyao.auth.authorize.exception.DataException;
//import com.guoyao.auth.authorize.model.User;
//import com.guoyao.auth.authorize.web.consts.AppConstants;
//import com.guoyao.auth.authorize.web.consts.ServerCode;
//import com.guoyao.auth.authorize.web.controller.AbstractController;
//import com.guoyao.auth.authorize.web.controller.converter.UserConverter;
//import com.guoyao.auth.authorize.web.controller.query.UserQueryCondition;
//import com.guoyao.auth.authorize.web.form.UserForm;
//import com.guoyao.auth.authorize.web.form.UserFormUpdate;
//
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author wuchao
// * @Date 【2019年1月17日:上午11:27:53】
// */
//@Slf4j
//@RestController
//@RequestMapping("/user")
//public class UserController extends AbstractController {
//	@ApiOperation(value = "用户创建服务")
//	@PostMapping
//	public UserForm create(@Valid @RequestBody UserForm userForm,BindingResult errors) {
//		User user = UserConverter.form2EntityCreate(userForm);
//		userForm = UserConverter.entity2FormCreate(userService.save(user));
//		return userForm;
//	}
//	
//	@ApiOperation(value = "用户更新服务")
//	@PutMapping("/{uid:\\d+}")
//	public UserFormUpdate update(@PathVariable Long uid,@Valid @RequestBody UserFormUpdate userForm,BindingResult errors) {
//		User user = userService.findOne(uid);
//		if(user == null) throw new DataException(ServerCode.PC_DATA_NOT_EXIST);
//		UserConverter.form2EntityUpdate(userForm,user);
//		userService.save(user);
//		UserConverter.entity2FormUpdate(user,userForm);
//		return userForm;
//	}
//	
//	@ApiOperation(value = "用户删除服务")
//	@DeleteMapping("/{id:\\d+}")
//	public void delete(@PathVariable Long id) {
//		userService.delete(id);
//	}
//	
//	@ApiOperation(value = "用户查询服务")
//	@GetMapping
//	public Page<UserForm> query(UserQueryCondition condition, @PageableDefault(
//		page = AppConstants.AUTHORIZE_CONTROLLER_PAGE,
//		size = AppConstants.AUTHORIZE_CONTROLLER_SIZE,
//		sort = AppConstants.AUTHORIZE_CONTROLLER_SORT,
//		direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<UserForm> page = userService.findByCondition(condition,pageable);
//		return page;
//	}
//	
//	@ApiOperation(value = "用户信息服务")
//	@JsonView(UserForm.TwoLevelView.class)
//	@GetMapping("/{id:\\d+}")
//	public UserForm getInfo(@ApiParam("用户id") @PathVariable Long id) {
//		User user = userService.findOne(id);
//		if(user == null) throw new DataException(ServerCode.PC_DATA_NOT_EXIST);
//		UserForm userForm = UserConverter.entity2FormCreate(user);
//		return userForm;
//	}
//}
