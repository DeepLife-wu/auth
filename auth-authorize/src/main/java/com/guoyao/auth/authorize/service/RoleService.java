/**
 * 
 */
package com.guoyao.auth.authorize.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.guoyao.auth.authorize.model.Role;
import com.guoyao.auth.authorize.web.controller.query.RoleQueryCondition;
import com.guoyao.auth.authorize.web.form.RoleForm;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:24:23】
 */
public interface RoleService {

	Role save(Role role);

	Role findOne(Long rid);

	void delete(Long id);

	Page<RoleForm> findByCondition(RoleQueryCondition condition, Pageable pageable);

	List<Role> findAll();

	Role findByCode(String roleBrowse);

}
