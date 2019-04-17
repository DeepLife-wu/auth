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

import com.guoyao.auth.authorize.model.Role;
import com.guoyao.auth.authorize.repository.RoleRepository;
import com.guoyao.auth.authorize.repository.spec.RoleSpec;
import com.guoyao.auth.authorize.repository.support.QueryResultConverter;
import com.guoyao.auth.authorize.service.RoleService;
import com.guoyao.auth.authorize.web.controller.query.RoleQueryCondition;
import com.guoyao.auth.authorize.web.form.RoleForm;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:24:35】
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findOne(Long rid) {
		return roleRepository.findOne(rid);
	}

	@Override
	public void delete(Long id) {
		roleRepository.delete(id);
	}

	@Override
	public Page<RoleForm> findByCondition(RoleQueryCondition condition, Pageable pageable) {
		Page<Role> roles = roleRepository.findAll(new RoleSpec(condition),pageable);
		return QueryResultConverter.convert(roles, RoleForm.class, pageable);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findByCode(String code) {
		return roleRepository.findByCode(code);
	}
}
