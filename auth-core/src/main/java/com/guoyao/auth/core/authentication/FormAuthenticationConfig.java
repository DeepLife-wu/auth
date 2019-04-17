/**
 * 
 */
package com.guoyao.auth.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.guoyao.auth.core.properties.SecurityConstants;

/**表单登录配置
 * @author wuchao
 * @Date 【2019年2月13日:下午5:42:17】
 */
@Component
public class FormAuthenticationConfig {

	@Autowired
	protected AuthenticationSuccessHandler guoyaoAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler guoyaoAuthenctiationFailureHandler;
	
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
			.successHandler(guoyaoAuthenticationSuccessHandler)
			.failureHandler(guoyaoAuthenctiationFailureHandler);
	}
}
