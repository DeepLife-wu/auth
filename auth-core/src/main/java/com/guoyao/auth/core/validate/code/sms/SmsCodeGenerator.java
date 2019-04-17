/**
 * 
 */
package com.guoyao.auth.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.guoyao.auth.core.properties.SecurityProperties;
import com.guoyao.auth.core.validate.code.ValidateCode;
import com.guoyao.auth.core.validate.code.ValidateCodeGenerator;

import lombok.Data;

/**短信验证码生成器
 * @author wuchao
 * @Date 【2019年2月13日:下午4:16:41】
 */
@Data
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
}
