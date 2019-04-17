/**
 * 
 */
package com.guoyao.auth.core.social.qq.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.guoyao.auth.core.social.qq.api.QQ;
import com.guoyao.auth.core.social.qq.api.QQImpl;

/**
 * @author wuchao
 * @Date 【2019年2月13日:下午5:58:23】
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	private String appId;
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	
	public QQServiceProvider(String appId, String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
	 */
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}
}
