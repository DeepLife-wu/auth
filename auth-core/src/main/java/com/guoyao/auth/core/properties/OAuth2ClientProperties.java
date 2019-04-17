/**
 * 
 */
package com.guoyao.auth.core.properties;

import lombok.Data;
import lombok.ToString;

/**认证服务器注册的第三方应用配置项
 * @author wuchao
 * @Date 【2019年2月13日:下午3:07:54】
 */
@Data
@ToString
public class OAuth2ClientProperties {

	/**
	 * 第三方应用appId
	 */
	private String clientId;
	/**
	 * 第三方应用appSecret
	 */
	private String clientSecret;
	/**
	 * 针对此应用发出的token的有效时间
	 */
	private int accessTokenValidateSeconds = 7200;
}
