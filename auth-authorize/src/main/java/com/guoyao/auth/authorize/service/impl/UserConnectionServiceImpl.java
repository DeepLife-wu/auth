/**
 * 
 */
package com.guoyao.auth.authorize.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guoyao.auth.authorize.model.UserConnection;
import com.guoyao.auth.authorize.model.embedd.GuoyaoUserConnectionId;
import com.guoyao.auth.authorize.repository.UserConnectionRepository;
import com.guoyao.auth.authorize.service.UserConnectionService;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:23:58】
 */
@Service
@Transactional
public class UserConnectionServiceImpl implements UserConnectionService {
	
	@Autowired
	private UserConnectionRepository userConnectionRepository;

	@Override
	public UserConnection findOne(GuoyaoUserConnectionId id) {
		return userConnectionRepository.findOne(id);
	}

	@Override
	public UserConnection findByUserId(String username) {
		return userConnectionRepository.findByUserId(username);
	}
}
