/**
 * 
 */
package com.guoyao.auth.authorize.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guoyao.auth.authorize.model.User;
import com.guoyao.auth.authorize.repository.UserRepository;
import com.guoyao.auth.authorize.repository.spec.UserSpec;
import com.guoyao.auth.authorize.repository.support.QueryResultConverter;
import com.guoyao.auth.authorize.service.UserService;
import com.guoyao.auth.authorize.web.controller.query.UserQueryCondition;
import com.guoyao.auth.authorize.web.form.UserForm;
import com.guoyao.auth.core.util.Validator;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:23:58】
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findOne(Long uid) {
		return userRepository.findOne(uid);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public Page<UserForm> findByCondition(UserQueryCondition condition, Pageable pageable) {
		Page<User> users = userRepository.findAll(new UserSpec(condition),pageable);
		Page<UserForm> convert = QueryResultConverter.convert(users, UserForm.class, pageable);
		if(CollectionUtils.isNotEmpty(convert.getContent())) {
			for(UserForm uf : convert.getContent()) {
				uf.setPassword("");
			}
		}
		return convert;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByAccount(String account) {
		if(Validator.isPhone(account)) {
			return userRepository.findByPhone(account);
		}
		if(Validator.isEmail(account)) {
			return userRepository.findByEmail(account);
		}
		return userRepository.findByUsername(account);
	}
}
