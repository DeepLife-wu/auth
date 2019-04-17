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
//import com.guoyao.auth.authorize.exception.DataException;
//import com.guoyao.auth.authorize.model.Role;
//import com.guoyao.auth.authorize.web.consts.AppConstants;
//import com.guoyao.auth.authorize.web.consts.ServerCode;
//import com.guoyao.auth.authorize.web.controller.AbstractController;
//import com.guoyao.auth.authorize.web.controller.converter.RoleConverter;
//import com.guoyao.auth.authorize.web.controller.query.RoleQueryCondition;
//import com.guoyao.auth.authorize.web.form.RoleForm;
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
//@RequestMapping("/role")
//public class RoleController extends AbstractController {
//	@ApiOperation(value = "角色创建服务")
//	@PostMapping
//	public RoleForm create(@Valid @RequestBody RoleForm roleForm,BindingResult errors) {
//		Role role = RoleConverter.form2EntityCreate(roleForm);
//		roleForm = RoleConverter.entity2FormCreate(roleService.save(role));
//		return roleForm;
//	}
//	
//	@ApiOperation(value = "角色更新服务")
//	@PutMapping("/{rid:\\d+}")
//	public RoleForm update(@PathVariable Long rid,@Valid @RequestBody RoleForm roleForm,BindingResult errors) {
//		Role role = roleService.findOne(rid);
//		if(role == null) throw new DataException(ServerCode.PC_DATA_NOT_EXIST);
//		RoleConverter.form2EntityUpdate(roleForm,role);
//		roleService.save(role);
//		RoleConverter.entity2FormUpdate(role,roleForm);
//		return roleForm;
//	}
//	
//	@ApiOperation(value = "角色删除服务")
//	@DeleteMapping("/{id:\\d+}")
//	public void delete(@PathVariable Long id) {
//		roleService.delete(id);
//	}
//	
//	@ApiOperation(value = "角色查询服务")
//	@GetMapping
//	public Page<RoleForm> query(RoleQueryCondition condition, @PageableDefault(
//		page = AppConstants.AUTHORIZE_CONTROLLER_PAGE,
//		size = AppConstants.AUTHORIZE_CONTROLLER_SIZE,
//		sort = AppConstants.AUTHORIZE_CONTROLLER_SORT,
//		direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<RoleForm> page = roleService.findByCondition(condition,pageable);
//		return page;
//	}
//	
//	@ApiOperation(value = "角色信息服务")
//	@GetMapping("/{id:\\d+}")
//	public RoleForm getInfo(@ApiParam("角色id") @PathVariable Long id) {
//		Role role = roleService.findOne(id);
//		if(role == null) throw new DataException(ServerCode.PC_DATA_NOT_EXIST);
//		RoleForm roleForm = RoleConverter.entity2FormCreate(role);
//		return roleForm;
//	}
//}
