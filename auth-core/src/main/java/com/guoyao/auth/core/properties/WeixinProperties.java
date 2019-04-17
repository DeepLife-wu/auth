/**
 * 
 */
package com.guoyao.auth.core.properties;

import lombok.Data;
import lombok.ToString;

/**微信登录配置项
 * @author wuchao
 * @Date 【2019年2月13日:下午3:17:34】
 */
@Data
@ToString
public class WeixinProperties /*extends SocialProperties*/ {

	/**
	 * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
	 */
	private String providerId = "weixin";
	
	/**
	 * Application id.
	 */
	private String appId;

	/**
	 * Application secret.
	 */
	private String appSecret;
}
