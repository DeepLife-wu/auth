package com.guoyao.auth.authorize.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.guoyao.auth.authorize.model.User;
import com.guoyao.auth.authorize.web.controller.query.UserQueryCondition;
import com.guoyao.auth.authorize.web.form.UserForm;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:22:52】
 */
public interface UserService {

	User save(User user);

	User findOne(Long uid);

	void delete(Long id);

	Page<UserForm> findByCondition(UserQueryCondition condition, Pageable pageable);

	User findByUsername(String username);
	
	User findByAccount(String account);
}
