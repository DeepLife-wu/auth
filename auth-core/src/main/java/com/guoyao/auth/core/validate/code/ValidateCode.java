/**
 * 
 */
package com.guoyao.auth.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.guoyao.auth.core.properties.BrowserProperties;
import com.guoyao.auth.core.properties.OAuth2Properties;
import com.guoyao.auth.core.properties.SecurityProperties;
import com.guoyao.auth.core.properties.SocialProperties;
import com.guoyao.auth.core.properties.ValidateCodeProperties;

import lombok.Data;
import lombok.ToString;

/**验证码信息封装类
 * @author wuchao
 * @Date 【2019年2月13日:下午3:32:33】
 */
@Data
@ToString
public class ValidateCode implements Serializable {
	private String code;
	private LocalDateTime expireTime;
	
	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public ValidateCode(String code, LocalDateTime expireTime){
		this.code = code;
		this.expireTime = expireTime;
	}
	
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
