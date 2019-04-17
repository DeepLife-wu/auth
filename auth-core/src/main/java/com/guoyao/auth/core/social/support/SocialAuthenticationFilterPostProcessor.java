/**
 * 
 */
package com.guoyao.auth.core.social.support;

import org.springframework.social.security.SocialAuthenticationFilter;

/**SocialAuthenticationFilter后处理器，用于在不同环境下个性化社交登录的配置
 * @author wuchao
 * @Date 【2019年2月13日:下午6:02:37】
 */
public interface SocialAuthenticationFilterPostProcessor {
	/**
	 * @param socialAuthenticationFilter
	 */
	void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
