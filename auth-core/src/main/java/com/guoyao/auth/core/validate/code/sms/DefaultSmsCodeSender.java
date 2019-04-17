/**
 * 
 */
package com.guoyao.auth.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**默认的短信验证码发送器
 * @author wuchao
 * @Date 【2019年2月13日:下午4:18:18】
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

	/* (non-Javadoc)
	 * @see com.guoyao.auth.core.validate.code.sms.SmsCodeSender#send(java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String mobile, String code) {
		log.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
		log.info("向手机"+mobile+"发送短信验证码"+code);
	}
}
