/**
 * 
 */
package com.guoyao.auth.authorize.service.impl;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.guoyao.auth.authorize.authentication.AuthGrantedAuthority;
import com.guoyao.auth.authorize.service.RbacService;
import com.guoyao.auth.authorize.web.controller.AbstractController;

/**
 * @author wuchao
 * @Date 【2019年2月14日:下午2:54:31】
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	/* (non-Javadoc)
	 * @see com.guoyao.auth.authorize.service.RbacService#hasPermission(javax.servlet.http.HttpServletRequest, org.springframework.security.core.Authentication)
	 */
	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		boolean hasPermission = false;

		if (principal instanceof User) {
			//如果用户名是admin，就永远返回true
			if (StringUtils.equals(((User) principal).getUsername(), AbstractController.SUPER_ADMIN)) {
				hasPermission = true;
			} else {
				// 读取用户所拥有权限的所有URL
				Collection<GrantedAuthority> authorities = ((User) principal).getAuthorities();
				if(CollectionUtils.isNotEmpty(authorities)) {
					for(GrantedAuthority ga : authorities) {
						if(ga instanceof AuthGrantedAuthority) {
							AuthGrantedAuthority paga = (AuthGrantedAuthority)ga;
//							if(antPathMatcher.match(paga.getUrl(), request.getRequestURI()) && paga.getMethod().equals(request.getMethod())) {
							if(StringUtils.startsWith(request.getRequestURI(),paga.getUrl()) && paga.getMethod().equals(request.getMethod())) {
								hasPermission = true;
								break;
							}
						}
					}
				}
			}
		}
		return hasPermission;
	}

	@Override
	public boolean hasPermission(String url, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		boolean hasPermission = false;
		if (principal instanceof User) {
			//如果用户名是admin，就永远返回true
			if (StringUtils.equals(((User) principal).getUsername(), AbstractController.SUPER_ADMIN)) {
				hasPermission = true;
			} else {
				// 读取用户所拥有权限的所有URL
				Collection<GrantedAuthority> authorities = ((User) principal).getAuthorities();
				if(CollectionUtils.isNotEmpty(authorities)) {
					for(GrantedAuthority ga : authorities) {
						if(ga instanceof AuthGrantedAuthority) {
							AuthGrantedAuthority paga = (AuthGrantedAuthority)ga;
							if(StringUtils.startsWith(url,paga.getUrl())) {
								hasPermission = true;
								break;
							}
						}
					}
				}
			}
		}
		return hasPermission;
	}
}
