/**
 * 
 */
package com.guoyao.auth.core.validate.code.sms;

/**
 * @author wuchao
 * @Date 【2019年2月13日:下午4:13:11】
 */
public interface SmsCodeSender {
	/**
	 * @param mobile
	 * @param code
	 */
	void send(String mobile, String code);
}
