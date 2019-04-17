/**
 * 
 */
package com.guoyao.auth.core.authentication;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认的SocialUserDetailsService实现
 * 不做任何处理，只在控制台打印一句日志，然后抛出异常，提醒业务系统自己配置SocialUserDetailsService。
 * 
 * @author wuchao
 * @Date 【2019年2月13日:下午5:40:44】
 */
@Slf4j
public class DefaultSocialUserDetailsService implements SocialUserDetailsService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.social.security.SocialUserDetailsService#loadUserByUserId
	 * (java.lang.String)
	 */
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		log.warn("请配置 SocialUserDetailsService 接口的实现.");
		throw new UsernameNotFoundException(userId);
	}

}
