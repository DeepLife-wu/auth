/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.converter;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.guoyao.auth.authorize.model.Menu;
import com.guoyao.auth.authorize.model.Menu;
import com.guoyao.auth.authorize.web.form.MenuForm;
import com.guoyao.auth.authorize.web.form.MenuForm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月25日:下午4:04:35】
 */
@Slf4j
public class MenuConverter {

	public static Menu form2EntityCreate(MenuForm menuForm) {
		log.info("form2EntityCreate menu form {}",menuForm);
		Menu menu = Menu.builder().build();
		BeanUtils.copyProperties(menuForm, menu);
		menu.setCreateTime(new Date());
		log.info("form2EntityCreate menu entity {}",menu);
		return menu;
	}
	
	/**
	 * 把form中的字段部分更新到menu中
	 * @param menuForm
	 * @param menu
	 */
	public static void form2EntityUpdate(MenuForm menuForm, Menu menu) {
		log.info("form2EntityUpdate menu form {}",menuForm);
		menu.setName(menuForm.getName());
		menu.setSort(menuForm.getSort());
		log.info("form2EntityUpdate menu entity {}",menu);
	}
	
	public static MenuForm entity2FormCreate(Menu menu) {
		log.info("entity2Form menu entity {}",menu);
		MenuForm form = MenuForm.builder().build();
		BeanUtils.copyProperties(menu, form);
		log.info("entity2Form menu form {}",form);
		return form;
	}

	/**
	 * 把entity中的字段部分更新到menu中
	 * @param menu
	 * @param menuForm
	 */
	public static void entity2FormUpdate(Menu menu, MenuForm menuForm) {
		log.info("entity2FormUpdate menu entity {}",menu);
		menuForm.setId(menu.getId());
		menuForm.setParentId(menu.getParentId());
		log.info("entity2FormUpdate menu form {}",menuForm);
	}
}
