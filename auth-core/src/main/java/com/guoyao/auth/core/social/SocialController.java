/**
 * 
 */
package com.guoyao.auth.core.social;

import org.springframework.social.connect.Connection;

import com.guoyao.auth.core.social.support.SocialUserInfo;

/**
 * @author wuchao
 * @Date 【2019年2月13日:下午6:17:35】
 */
public abstract class SocialController {

	/**
	 * 根据Connection信息构建SocialUserInfo
	 * @param connection
	 * @return
	 */
	protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
		SocialUserInfo userInfo = new SocialUserInfo();
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeadimg(connection.getImageUrl());
		return userInfo;
	}
}