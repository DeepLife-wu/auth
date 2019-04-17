/**
 * 
 */
package com.guoyao.auth.core.properties;

import lombok.Data;
import lombok.ToString;

/**验证码配置
 * @author wuchao
 * @Date 【2019年2月13日:下午3:16:11】
 */
@Data
@ToString
public class ValidateCodeProperties {

	/**
	 * 图片验证码配置
	 */
	private ImageCodeProperties image = new ImageCodeProperties();
	/**
	 * 短信验证码配置
	 */
	private SmsCodeProperties sms = new SmsCodeProperties();
}
