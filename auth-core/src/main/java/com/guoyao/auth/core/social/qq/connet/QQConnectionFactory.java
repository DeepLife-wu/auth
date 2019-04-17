/**
 * 
 */
package com.guoyao.auth.core.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.guoyao.auth.core.social.qq.api.QQ;

/**
 * @author wuchao
 * @Date 【2019年2月13日:下午5:56:35】
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}
}
