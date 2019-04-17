/**
 * 
 */
package com.guoyao.auth.core.social.qq.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.guoyao.auth.core.social.qq.api.QQ;
import com.guoyao.auth.core.social.qq.api.QQUserInfo;

/**
 * @author wuchao
 * @Date 【2019年2月13日:下午5:55:51】
 */
public class QQAdapter implements ApiAdapter<QQ> {
	
	@Override
	public boolean test(QQ api) {
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		//do noting
	}
}
