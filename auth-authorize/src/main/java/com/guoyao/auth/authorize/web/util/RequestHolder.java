package com.guoyao.auth.authorize.web.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.guoyao.auth.authorize.model.User;
import com.guoyao.auth.authorize.web.controller.AbstractController;

public class RequestHolder {
    public static void setCurrentUser(User user){
    	request().getSession().setAttribute(AbstractController.LOGIN_USER_KEY, user);
    }

    public static User getCurrentUser(){
    	User loginUser = (User)request().getSession().getAttribute(AbstractController.LOGIN_USER_KEY);
		return loginUser != null ? loginUser : 
			User.builder().username(AbstractController.AUTH_BROWSER)
						  .name(AbstractController.AUTH_BROWSER).build();
    }

    public static void removeCurrentUser() {
    	request().getSession().removeAttribute(AbstractController.LOGIN_USER_KEY);
    }
    
    public static boolean isLogin() {
    	User loginUser = (User)request().getSession().getAttribute(AbstractController.LOGIN_USER_KEY);
    	return loginUser != null ? true : false;
    }
    
    public static HttpServletRequest request() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
}