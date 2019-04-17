/**
 * 
 */
package com.guoyao.auth.authorize.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.guoyao.auth.authorize.model.Permission;
import com.guoyao.auth.authorize.web.controller.query.PermissionQueryCondition;
import com.guoyao.auth.authorize.web.form.PermissionForm;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:24:52】
 */
public interface PermissionService {

	Permission save(Permission permission);

	Permission findOne(Long id);

	void delete(Long id);

	Page<PermissionForm> findByCondition(PermissionQueryCondition condition, Pageable pageable);

	List<Permission> findByType(Integer menuType);

	List<Permission> findAll();

}
