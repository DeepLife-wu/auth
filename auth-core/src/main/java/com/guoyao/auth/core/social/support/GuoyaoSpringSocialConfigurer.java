/**
 * 
 */
package com.guoyao.auth.core.social.support;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

import lombok.Data;

/**继承默认的社交登录配置，加入自定义的后处理逻辑
 * @author wuchao
 * @Date 【2019年2月13日:下午6:01:33】
 */
@Data
public class GuoyaoSpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

	public GuoyaoSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.SecurityConfigurerAdapter#postProcess(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		if (socialAuthenticationFilterPostProcessor != null) {
			socialAuthenticationFilterPostProcessor.process(filter);
		}
		return (T) filter;
	}
}

