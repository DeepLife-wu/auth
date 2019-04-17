/**
 * 
 */
package com.guoyao.auth.authorize.authentication;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.guoyao.auth.core.authorize.AuthorizeConfigProvider;

/**
 * @author wuchao
 * @Date 【2019年2月14日:上午11:44:48】
 */
@Component
@Order(Integer.MAX_VALUE)
public class RbacAuthorizeConfigProvider implements AuthorizeConfigProvider {

	/* (non-Javadoc)
	 * @see com.imooc.security.core.authorize.AuthorizeConfigProvider#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(HttpMethod.GET, 
				"/book/**/*.html",
				"/book/**/*",
				"/bootstrap3.3.5/**/*.*",
				"/logout",
				"/**/*.xml"
				).permitAll()
		.antMatchers(HttpMethod.POST,
				"/user/regist"
				).permitAll()
		.antMatchers(HttpMethod.GET, 
				"/**/*.html",
				"/**/*.ftl"
				).authenticated()
		.anyRequest().access("@rbacService.hasPermission(request, authentication)");
//		,"/user/regist","/auth/qq","/auth/weixin"
		return true;
	}
}
