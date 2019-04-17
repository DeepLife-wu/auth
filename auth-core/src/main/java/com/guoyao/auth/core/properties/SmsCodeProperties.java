/**
 * 
 */
package com.guoyao.auth.core.properties;

import lombok.Data;
import lombok.ToString;

/**
 * @author wuchao
 * @Date 【2019年2月13日:下午3:05:54】
 */
@Data
@ToString
public class SmsCodeProperties {

	/**
	 * 验证码长度
	 */
	private int length = 6;
	/**
	 * 过期时间
	 */
	private int expireIn = 60;
	/**
	 * 要拦截的url，多个url用逗号隔开，ant pattern
	 */
	private String url;
}
