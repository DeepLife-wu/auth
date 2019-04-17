/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.converter;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.guoyao.auth.authorize.model.Role;
import com.guoyao.auth.authorize.web.form.RoleForm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月23日:下午4:41:31】
 */
@Slf4j
public class RoleConverter {
	public static Role form2EntityCreate(RoleForm roleForm) {
		log.info("form2EntityCreate role form {}",roleForm);
		Role role = Role.builder().build();
		BeanUtils.copyProperties(roleForm, role);
		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		log.info("form2EntityCreate role entity {}",role);
		return role;
	}
	
	/**
	 * 把form中的字段部分更新到role中
	 * @param roleForm
	 * @param role
	 */
	public static void form2EntityUpdate(RoleForm roleForm, Role role) {
		log.info("form2EntityUpdate role form {}",roleForm);
		role.setCode(roleForm.getCode());
		role.setName(roleForm.getName());
		role.setUpdateTime(new Date());
		log.info("form2EntityUpdate role entity {}",role);
	}
	
	public static RoleForm entity2FormCreate(Role role) {
		log.info("entity2Form Role entity {}",role);
		RoleForm form = RoleForm.builder().build();
		BeanUtils.copyProperties(role, form);
		log.info("entity2Form Role form {}",form);
		return form;
	}

	/**
	 * 把entity中的字段部分更新到role中
	 * @param role
	 * @param roleForm
	 */
	public static void entity2FormUpdate(Role role, RoleForm roleForm) {
		log.info("entity2FormUpdate Role entity {}",role);
		roleForm.setId(role.getId());
		roleForm.setUpdateTime(role.getUpdateTime());
		roleForm.setCreateTime(role.getCreateTime());
		log.info("entity2FormUpdate Role form {}",roleForm);
	}
}
