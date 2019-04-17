/**
 * 
 */
package com.guoyao.auth.authorize.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

/**
 * @author wuchao
 * @Date 【2019年2月14日:下午2:54:00】
 */
public interface RbacService {

	boolean hasPermission(HttpServletRequest request, Authentication authentication);
	
	boolean hasPermission(String url, Authentication authentication);
}
