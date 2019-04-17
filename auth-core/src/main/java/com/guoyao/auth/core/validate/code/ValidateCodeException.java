/**
 * 
 */
package com.guoyao.auth.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author wuchao
 * @Date 【2019年2月13日:下午3:39:52】
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2492752469635555419L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
