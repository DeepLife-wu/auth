/**
 * 
 */
package com.guoyao.auth.authorize.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guoyao.auth.authorize.model.Menu;
import com.guoyao.auth.authorize.model.Permission;
import com.guoyao.auth.authorize.repository.MenuRepository;
import com.guoyao.auth.authorize.repository.PermissionRepository;
import com.guoyao.auth.authorize.repository.spec.ChildMenuSpec;
import com.guoyao.auth.authorize.repository.spec.MenuSpec;
import com.guoyao.auth.authorize.repository.support.QueryResultConverter;
import com.guoyao.auth.authorize.service.MenuService;
import com.guoyao.auth.authorize.web.controller.query.ChildMenuQueryCondition;
import com.guoyao.auth.authorize.web.controller.query.MenuQueryCondition;
import com.guoyao.auth.authorize.web.form.MenuForm;

/**
 * @author wuchao
 * @Date 【2019年1月25日:下午3:46:20】
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private PermissionRepository permissionRepository;

	/* (non-Javadoc)
	 * @see com.guoyao.auth.authorize.service.MenuService#save(com.guoyao.auth.authorize.model.Menu)
	 */
	@Override
	public Menu save(Menu menu) {
		return menuRepository.save(menu);
	}

	/* (non-Javadoc)
	 * @see com.guoyao.auth.authorize.service.MenuService#findOne(java.lang.Long)
	 */
	@Override
	public Menu findOne(Long mid) {
		return menuRepository.findOne(mid);
	}

	/* (non-Javadoc)
	 * @see com.guoyao.auth.authorize.service.MenuService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		menuRepository.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.guoyao.auth.authorize.service.MenuService#findByCondition(com.guoyao.auth.authorize.web.controller.query.MenuQueryCondition, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<MenuForm> findByCondition(MenuQueryCondition condition, Pageable pageable) {
		Page<Menu> menus = menuRepository.findAll(new MenuSpec(condition),pageable);
		Page<MenuForm> convert = QueryResultConverter.convert(menus, MenuForm.class, pageable);
		return convert;
	}
	
	@Override
	public Page<MenuForm> findByCondition(ChildMenuQueryCondition condition, Pageable pageable) {
		Page<Menu> menus = menuRepository.findAll(new ChildMenuSpec(condition),pageable);
		Page<MenuForm> convert = QueryResultConverter.convert(menus, MenuForm.class, pageable);
		return convert;
	}

	@Override
	public List<MenuForm> findRootMenu() {
		List<Menu> menuList = menuRepository.findRootMenu();
		List<MenuForm> menuFormList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(menuList)) {
			for(Menu menu : menuList) {
				MenuForm menuForm = MenuForm.builder().id(menu.getId()).createTime(menu.getCreateTime()).name(menu.getName())
								  .menuUrl(menu.getMenuUrl()).sort(menu.getSort()).parentId(menu.getParentId())
								  .build();
				if(CollectionUtils.isNotEmpty(menu.getChildrenList())) {
					for(Menu child : menu.getChildrenList())
					{
						MenuForm mf = MenuForm.builder().id(child.getId()).createTime(child.getCreateTime()).name(child.getName())
								  .menuUrl(child.getMenuUrl()).sort(child.getSort()).parentId(child.getParentId())
								  .build();
						menuForm.getChildrenList().add(mf);
					}
				}
				menuFormList.add(menuForm);
			}
		}
		return menuFormList;
	}

	@Override
	public void saveChildMenu(Long parentId, String ids) {
		String[] permissionIds = StringUtils.split(ids,",");
		if(ArrayUtils.isNotEmpty(permissionIds)) {
			Integer seq = 1;
			for(String pid : permissionIds) {
				if(NumberUtils.isNumber(pid)) {
					Long permissionId = NumberUtils.toLong(pid);
					Permission permission = permissionRepository.findOne(permissionId);
					
					Menu menu = Menu.builder().name(permission.getName()).menuUrl(permission.getUrl())
								  .parentId(parentId)
								  .createTime(new Date())
								  .sort(seq++).build();
					menuRepository.save(menu);
				}
			}
		}
	}
}
