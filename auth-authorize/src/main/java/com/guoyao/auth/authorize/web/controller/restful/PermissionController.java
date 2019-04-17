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
//import com.guoyao.auth.authorize.model.Permission;
//import com.guoyao.auth.authorize.web.consts.AppConstants;
//import com.guoyao.auth.authorize.web.consts.ServerCode;
//import com.guoyao.auth.authorize.web.controller.AbstractController;
//import com.guoyao.auth.authorize.web.controller.converter.PermissionConverter;
//import com.guoyao.auth.authorize.web.controller.query.PermissionQueryCondition;
//import com.guoyao.auth.authorize.web.form.PermissionForm;
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
//@RequestMapping("/permission")
//public class PermissionController extends AbstractController {
//	@ApiOperation(value = "权限创建服务")
//	@PostMapping
//	public PermissionForm create(@Valid @RequestBody PermissionForm permissionForm,BindingResult errors) {
//		Permission permission = PermissionConverter.form2EntityCreate(permissionForm);
//		permissionForm = PermissionConverter.entity2FormCreate(permissionService.save(permission));
//		return permissionForm;
//	}
//	
//	@ApiOperation(value = "权限更新服务")
//	@PutMapping("/{pid:\\d+}")
//	public PermissionForm update(@PathVariable Long pid,@Valid @RequestBody PermissionForm permissionForm,BindingResult errors) {
//		Permission permission = permissionService.findOne(pid);
//		if(permission == null)
//			throw new DataException(ServerCode.PC_DATA_NOT_EXIST);
//		PermissionConverter.form2EntityUpdate(permissionForm, permission);
//		permissionService.save(permission);
//		PermissionConverter.entity2FormUpdate(permission,permissionForm);
//		return permissionForm;
//	}
//	
//	@ApiOperation(value = "权限删除服务")
//	@DeleteMapping("/{id:\\d+}")
//	public void delete(@PathVariable Long id) {
//		permissionService.delete(id);
//	}
//	
//	@ApiOperation(value = "权限查询服务")
//	@GetMapping
//	public Page<PermissionForm> query(PermissionQueryCondition condition, @PageableDefault(
//		page = AppConstants.AUTHORIZE_CONTROLLER_PAGE,
//		size = AppConstants.AUTHORIZE_CONTROLLER_SIZE,
//		sort = AppConstants.AUTHORIZE_CONTROLLER_SORT,
//		direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<PermissionForm> page = permissionService.findByCondition(condition,pageable);
//		return page;
//	}
//	
//	@ApiOperation(value = "权限信息服务")
//	@GetMapping("/{id:\\d+}")
//	public PermissionForm getInfo(@ApiParam("权限id") @PathVariable Long id) {
//		Permission permission = permissionService.findOne(id);
//		if(permission == null)
//			throw new DataException(ServerCode.PC_DATA_NOT_EXIST);
//		PermissionForm permissionForm = PermissionConverter.entity2FormCreate(permission);
//		return permissionForm;
//	}
//}
