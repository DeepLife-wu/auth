/**
 * 
 */
package com.guoyao.auth.authorize.web.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;

import com.guoyao.auth.authorize.authentication.AuthGrantedAuthority;

/**
 * @author wuchao
 * @Date 【2019年2月15日:下午2:11:38】
 */
public class SecurityUtils {

	/**
	 * <b>获取当前登录用户的所有权限</b>
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getUserAuthorities() {
		HttpSession session = RequestHolder.request().getSession();
		SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		List<GrantedAuthority> securityList = (List<GrantedAuthority>) securityContext.getAuthentication().getAuthorities();
		List<String> permissionUrlList = new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(securityList)) {
			for (GrantedAuthority grantedAuthority : securityList) {
				if(grantedAuthority instanceof AuthGrantedAuthority) {
					AuthGrantedAuthority paga = (AuthGrantedAuthority)grantedAuthority;
					permissionUrlList.add(paga.getUrl());
				}
			}
		}
		return permissionUrlList;
	}
	
	/**
	 * <b>获取当前登录用户的账号名称</b>
	 * @return username
	 */
	public static String getLoginName() {
		HttpSession session = RequestHolder.request().getSession();
		SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		return securityContext.getAuthentication().getName();
	}
}
