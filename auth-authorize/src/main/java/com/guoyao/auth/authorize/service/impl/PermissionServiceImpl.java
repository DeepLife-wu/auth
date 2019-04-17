/**
 * 
 */
package com.guoyao.auth.authorize.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guoyao.auth.authorize.model.Permission;
import com.guoyao.auth.authorize.repository.PermissionRepository;
import com.guoyao.auth.authorize.repository.spec.PermissionSpec;
import com.guoyao.auth.authorize.repository.support.QueryResultConverter;
import com.guoyao.auth.authorize.service.PermissionService;
import com.guoyao.auth.authorize.web.controller.query.PermissionQueryCondition;
import com.guoyao.auth.authorize.web.form.PermissionForm;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:25:08】
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Permission save(Permission permission) {
		return permissionRepository.save(permission);
	}

	@Override
	public Permission findOne(Long id) {
		return permissionRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		permissionRepository.delete(id);
	}

	@Override
	public Page<PermissionForm> findByCondition(PermissionQueryCondition condition, Pageable pageable) {
		Page<Permission> permissions = permissionRepository.findAll(new PermissionSpec(condition),pageable);
		return QueryResultConverter.convert(permissions, PermissionForm.class, pageable);
	}

	@Override
	public List<Permission> findByType(Integer menuType) {
		List<Permission> permissionList = permissionRepository.findByType(menuType);
		return permissionList;
	}

	@Override
	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}
}
