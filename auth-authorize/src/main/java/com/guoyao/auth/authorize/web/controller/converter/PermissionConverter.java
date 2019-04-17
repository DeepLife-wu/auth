/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.converter;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.guoyao.auth.authorize.model.Permission;
import com.guoyao.auth.authorize.web.form.PermissionForm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月21日:下午3:21:37】
 */
@Slf4j
public class PermissionConverter {
	public static Permission form2EntityCreate(PermissionForm permissionForm) {
		log.info("form2EntityCreate permission form {}",permissionForm);
		Permission permission = Permission.builder().build();
		BeanUtils.copyProperties(permissionForm, permission);
		permission.setCreateTime(new Date());
		permission.setUpdateTime(new Date());
		log.info("form2EntityCreate permission entity {}",permission);
		return permission;
	}
	
	/**
	 * 把form中的字段部分更新到permission中
	 * @param permissionForm
	 * @param permission
	 */
	public static void form2EntityUpdate(PermissionForm permissionForm, Permission permission) {
		log.info("form2EntityUpdate permission form {}",permissionForm);
		permission.setName(permissionForm.getName());
		permission.setUrl(permissionForm.getUrl());
		permission.setRequestMethod(permissionForm.getRequestMethod());
		permission.setType(permissionForm.getType());
		permission.setUpdateTime(new Date());
		log.info("form2EntityUpdate permission entity {}",permission);
	}
	
	public static PermissionForm entity2FormCreate(Permission permission) {
		log.info("entity2FormCreate permission entity {}",permission);
		PermissionForm form = PermissionForm.builder().build();
		BeanUtils.copyProperties(permission, form);
		log.info("entity2FormCreate permission form {}",form);
		return form;
	}

	/**
	 * 把entity中的字段部分更新到permission中
	 * @param permission
	 * @param permissionForm
	 */
	public static void entity2FormUpdate(Permission permission, PermissionForm permissionForm) {
		log.info("entity2FormUpdate permission entity {}",permissionForm);
		BeanUtils.copyProperties(permission, permissionForm);
		log.info("entity2FormUpdate permission form {}",permissionForm);
	}

}
