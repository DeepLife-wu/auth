/**
 * 
 */
package com.guoyao.auth.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

import lombok.Data;
import lombok.ToString;

/**QQ登录配置项
 * @author wuchao
 * @Date 【2019年2月13日:下午3:09:26】
 */
@Data
@ToString
public class QQProperties /*extends SocialProperties*/ {

	/**
	 * 第三方id，用来决定发起第三方登录的url，默认是 qq。
	 */
	private String providerId = "qq";
	
	/**
	 * Application id.
	 */
	private String appId;

	/**
	 * Application secret.
	 */
	private String appSecret;
}
