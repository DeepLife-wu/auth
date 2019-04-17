/**
 * 
 */
package com.guoyao.auth.core.properties;

import lombok.Data;
import lombok.ToString;

/**
 * @author wuchao
 * @Date 【2019年2月13日:下午3:08:33】
 */
@Data
@ToString
public class OAuth2Properties {

	/**
	 * 使用jwt时为token签名的秘钥
	 */
	private String jwtSigningKey = "guoyao";
	/**
	 * 客户端配置
	 */
	private OAuth2ClientProperties[] clients = {};
}
