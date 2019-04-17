package com.guoyao.auth.authorize.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.guoyao.auth.authorize.model.Menu;
import com.guoyao.auth.authorize.web.controller.query.ChildMenuQueryCondition;
import com.guoyao.auth.authorize.web.controller.query.MenuQueryCondition;
import com.guoyao.auth.authorize.web.form.MenuForm;

/**
 * 
 * @author wuchao
 * @Date 【2019年1月25日:下午3:30:17】
 */
public interface MenuService {

	Menu save(Menu menu);
	void saveChildMenu(Long parentId, String ids);

	Menu findOne(Long mid);
	void delete(Long id);

	Page<MenuForm> findByCondition(MenuQueryCondition condition, Pageable pageable);
	Page<MenuForm> findByCondition(ChildMenuQueryCondition condition, Pageable pageable);

	List<MenuForm> findRootMenu();
	
}
