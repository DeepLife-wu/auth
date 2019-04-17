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
//import com.guoyao.auth.authorize.model.Menu;
//import com.guoyao.auth.authorize.web.consts.AppConstants;
//import com.guoyao.auth.authorize.web.consts.ServerCode;
//import com.guoyao.auth.authorize.web.controller.AbstractController;
//import com.guoyao.auth.authorize.web.controller.converter.MenuConverter;
//import com.guoyao.auth.authorize.web.controller.query.MenuQueryCondition;
//import com.guoyao.auth.authorize.web.form.MenuForm;
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
//@RequestMapping("/menu")
//public class MenuController extends AbstractController {
//	
//	@ApiOperation(value = "菜单创建服务")
//	@PostMapping
//	public MenuForm create(@Valid @RequestBody MenuForm menuForm,BindingResult errors) {
//		Menu menu = MenuConverter.form2EntityCreate(menuForm);
//		menuForm = MenuConverter.entity2FormCreate(menuService.save(menu));
//		return menuForm;
//	}
//	
//	@ApiOperation(value = "菜单更新服务")
//	@PutMapping("/{mid:\\d+}")
//	public MenuForm update(@PathVariable Long mid,@Valid @RequestBody MenuForm menuForm,BindingResult errors) {
//		Menu menu = menuService.findOne(mid);
//		if(menu == null) throw new DataException(ServerCode.PC_DATA_NOT_EXIST);
//		MenuConverter.form2EntityUpdate(menuForm,menu);
//		menuService.save(menu);
//		MenuConverter.entity2FormUpdate(menu,menuForm);
//		return menuForm;
//	}
//	
//	@ApiOperation(value = "菜单删除服务")
//	@DeleteMapping("/{id:\\d+}")
//	public void delete(@PathVariable Long id) {
//		menuService.delete(id);
//	}
//	
//	@ApiOperation(value = "菜单查询服务")
//	@GetMapping
//	public Page<MenuForm> query(MenuQueryCondition condition, @PageableDefault(
//		page = AppConstants.AUTHORIZE_CONTROLLER_PAGE,
//		size = AppConstants.AUTHORIZE_CONTROLLER_SIZE,
//		sort = AppConstants.AUTHORIZE_CONTROLLER_SORT,
//		direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<MenuForm> page = menuService.findByCondition(condition,pageable);
//		return page;
//	}
//	
//	@ApiOperation(value = "菜单信息服务")
//	@GetMapping("/{id:\\d+}")
//	public MenuForm getInfo(@ApiParam("菜单id") @PathVariable Long id) {
//		Menu menu = menuService.findOne(id);
//		if(menu == null) throw new DataException(ServerCode.PC_DATA_NOT_EXIST);
//		MenuForm MenuForm = MenuConverter.entity2FormCreate(menu);
//		return MenuForm;
//	}
//}
