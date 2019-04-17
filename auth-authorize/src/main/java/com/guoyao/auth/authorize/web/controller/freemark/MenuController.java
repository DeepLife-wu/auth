/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.freemark;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoyao.auth.authorize.annotation.SysLog;
import com.guoyao.auth.authorize.exception.DwzDataException;
import com.guoyao.auth.authorize.model.Menu;
import com.guoyao.auth.authorize.model.Permission;
import com.guoyao.auth.authorize.model.enums.LinkType;
import com.guoyao.auth.authorize.web.consts.AppConstants;
import com.guoyao.auth.authorize.web.consts.ServerCode;
import com.guoyao.auth.authorize.web.controller.AbstractController;
import com.guoyao.auth.authorize.web.controller.converter.MenuConverter;
import com.guoyao.auth.authorize.web.controller.query.ChildMenuQueryCondition;
import com.guoyao.auth.authorize.web.controller.query.MenuQueryCondition;
import com.guoyao.auth.authorize.web.dwz.CallbackType;
import com.guoyao.auth.authorize.web.dwz.DWZResponse;
import com.guoyao.auth.authorize.web.dwz.DwzCode;
import com.guoyao.auth.authorize.web.form.MenuForm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:27:53】
 */
@Slf4j
@Controller
@RequestMapping("/dwz/menu")
public class MenuController extends AbstractController {
	
	@RequestMapping("/list")
	public String list(String tabId,MenuQueryCondition condition,
			@RequestParam(value="pageNum",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_PAGE) Integer pageNum,
            @RequestParam(value="numPerPage",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_SIZE) Integer numPerPage,
    		Model model) {
		if(StringUtils.isNotBlank(tabId)) super.tabId = tabId;
		List<Order> orders=new ArrayList<Sort.Order>();
        orders.add(new Order(Direction.DESC, AppConstants.AUTHORIZE_CONTROLLER_SORT));
        Pageable pageable = new PageRequest(pageNum - 1,numPerPage,new Sort(orders));
		Page<MenuForm> page = menuService.findByCondition(condition,pageable);
		
		model.addAttribute("condition",condition);
		model.addAttribute("page", page);
		return "/dwz/menu/list";
	}
	
	@GetMapping("/add")
	public String getAdd() {
		return "/dwz/menu/add";
	}
	
	@SysLog("添加父菜单")
	@PostMapping("/add")
	@ResponseBody
	public DWZResponse postAdd(@Valid MenuForm menuForm,BindingResult errors) {
		Menu menu = MenuConverter.form2EntityCreate(menuForm);
		menuService.save(menu);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
							 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}
	
	@GetMapping("/update/{mid}")
	public String getUpdate(@PathVariable Long mid,Model model) {
		Menu menu = menuService.findOne(mid);
		if(menu == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		MenuForm menuForm = MenuConverter.entity2FormCreate(menu);
		model.addAttribute("menuForm", menuForm);
		return "/dwz/menu/update";
	}
	
	@SysLog("更新父菜单")
	@PostMapping("/update/{mid}")
	@ResponseBody
	public DWZResponse postUpdate(@PathVariable Long mid,@Valid MenuForm menuForm,BindingResult errors,Model model) {
		Menu menu = menuService.findOne(mid);
		if(menu == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		MenuConverter.form2EntityUpdate(menuForm,menu);
		menuService.save(menu);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.closeCurrent).build();
	}
	
	@SysLog("删除父菜单")
	@PostMapping("/delete/{mid}")
	@ResponseBody
	public DWZResponse delete(@PathVariable Long mid) {
		Menu menu = menuService.findOne(mid);
		if(menu == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		if(CollectionUtils.isNotEmpty(menu.getChildrenList())) {
			throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_MENU_HAS_CHILD_DATA.getMessage());
		}
		menuService.delete(mid);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(tabId).callbackType(CallbackType.forward).forwardUrl("dwz/menu/list").build();
	}
	
//////////////////////////////////////子菜单管理//////////////////////////////////
	private String childTabId = "childTabId";
	@RequestMapping("/child/list")
	public String childList(ChildMenuQueryCondition condition,
			@RequestParam(value="pageNum",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_PAGE) Integer pageNum,
            @RequestParam(value="numPerPage",defaultValue = AppConstants.AUTHORIZE_CONTROLLER_SIZE) Integer numPerPage,
    		Model model) {
		List<Order> orders=new ArrayList<Sort.Order>();
        orders.add(new Order(Direction.DESC, AppConstants.AUTHORIZE_CONTROLLER_SORT));
        Pageable pageable = new PageRequest(pageNum - 1,numPerPage,new Sort(orders));
		Page<MenuForm> page = menuService.findByCondition(condition,pageable);
		
		model.addAttribute("condition",condition);
		model.addAttribute("page", page);
		return "/dwz/menu/child/list";
	}
	
	@GetMapping("/child/add/{parentId}")
	public String getChildAdd(@PathVariable Long parentId,Model model) {
		Menu parentMenu = menuService.findOne(parentId);
		List<Menu> childList = parentMenu.getChildrenList();
		List<Permission> permissionList = permissionService.findByType(LinkType.MENU.getCode());
		
		if(CollectionUtils.isNotEmpty(childList)) {
			for(Menu cmenu : childList) {
				if(CollectionUtils.isNotEmpty(permissionList)) {
					for(Permission p : permissionList) {
						if(StringUtils.equalsIgnoreCase(p.getUrl(),cmenu.getMenuUrl())) {
							permissionList.remove(p);
							break;
						}
					}
				}
			}
		}
		model.addAttribute("parentId", parentId);
		model.addAttribute("permissionList", permissionList);
		return "/dwz/menu/child/add";
	}
	
	@SysLog("添加子菜单")
	@PostMapping("/child/add/{parentId}")
	@ResponseBody
	public DWZResponse postChildAdd(@PathVariable Long parentId,String ids) {
		Menu menu = menuService.findOne(parentId);
		if(menu == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		if(StringUtils.isBlank(ids)) {
			throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_ID_LIST_UNCHECK.getMessage());
		}
		menuService.saveChildMenu(parentId,ids);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
							 .navTabId(childTabId).callbackType(CallbackType.closeCurrent).build();
	}
	
	@SysLog("删除子菜单")
	@PostMapping("/child/delete/{cid}")
	@ResponseBody
	public DWZResponse deleteChild(@PathVariable Long cid) {
		Menu menu = menuService.findOne(cid);
		if(menu == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		if(CollectionUtils.isNotEmpty(menu.getChildrenList())) {
			throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_MENU_HAS_CHILD_DATA.getMessage());
		}
		menuService.delete(cid);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(childTabId).callbackType(CallbackType.forward).forwardUrl("dwz/menu/child/list").build();
	}
	
	@GetMapping("/child/update/{cid}")
	public String getChildUpdate(@PathVariable Long cid,Model model) {
		Menu menu = menuService.findOne(cid);
		if(menu == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		MenuForm menuForm = MenuConverter.entity2FormCreate(menu);
		model.addAttribute("menuForm", menuForm);
		return "/dwz/menu/child/update";
	}
	
	@SysLog("更新子菜单")
	@PostMapping("/child/update/{cid}")
	@ResponseBody
	public DWZResponse postChildUpdate(@PathVariable Long cid,@Valid MenuForm menuForm,BindingResult errors,Model model) {
		Menu menu = menuService.findOne(cid);
		if(menu == null) throw new DwzDataException(DwzCode.ERROR.getCode(),ServerCode.PC_DATA_NOT_EXIST.getMessage());
		MenuConverter.form2EntityUpdate(menuForm,menu);
		menuService.save(menu);
		return DWZResponse.builder().statusCode(DwzCode.OK.getCode()).message(DwzCode.OK.getMessage())
				 .navTabId(childTabId).callbackType(CallbackType.closeCurrent).build();
	}
}
