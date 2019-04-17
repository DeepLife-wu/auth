/**
 * 
 */
package com.guoyao.auth.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.guoyao.auth.browser.logout.GuoyaoLogoutSuccessHandler;
import com.guoyao.auth.browser.session.GuoyaoExpiredSessionStrategy;
import com.guoyao.auth.browser.session.GuoyaoInvalidSessionStrategy;
import com.guoyao.auth.core.decide.GuoyaoAccessDeineHandler;
import com.guoyao.auth.core.properties.SecurityProperties;

/**浏览器环境下扩展点配置，配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置。
 * @author wuchao
 * @Date 【2019年2月14日:上午9:44:49】
 */
@Configuration
public class BrowserSecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * session失效时的处理策略配置
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new GuoyaoInvalidSessionStrategy(securityProperties);
	}
	
	/**
	 * 并发登录导致前一个session失效时的处理策略配置
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new GuoyaoExpiredSessionStrategy(securityProperties);
	}
	
	/**
	 * 退出时的处理策略配置
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new GuoyaoLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
	}
	
	/**
	 * 访问拒绝时的处理策略配置
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(AccessDeniedHandler.class)
	public AccessDeniedHandler accessDeineHandler(){
		return new GuoyaoAccessDeineHandler();
	}
}
