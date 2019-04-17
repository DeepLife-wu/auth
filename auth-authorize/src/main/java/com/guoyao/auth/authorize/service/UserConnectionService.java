package com.guoyao.auth.authorize.service;

import com.guoyao.auth.authorize.model.UserConnection;
import com.guoyao.auth.authorize.model.embedd.GuoyaoUserConnectionId;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:22:52】
 */
public interface UserConnectionService {

	UserConnection findOne(GuoyaoUserConnectionId id);
	
	UserConnection findByUserId(String username);
}
