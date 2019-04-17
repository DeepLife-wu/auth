/**
 * 
 */
package com.guoyao.auth.authorize.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.social.connect.web.ProviderSignInUtils;

import com.alibaba.fastjson.JSONObject;
import com.guoyao.auth.authorize.service.LogService;
import com.guoyao.auth.authorize.service.MenuService;
import com.guoyao.auth.authorize.service.PermissionService;
import com.guoyao.auth.authorize.service.RoleService;
import com.guoyao.auth.authorize.service.UserConnectionService;
import com.guoyao.auth.authorize.service.UserService;
import com.guoyao.auth.authorize.web.dwz.DWZResponse;
import com.guoyao.auth.core.properties.SecurityProperties;

/**
 * @author wuchao
 * @Date 【2019年1月24日:下午2:40:15】
 */
public abstract class AbstractController {
	/** 密码加密规则(BCrypt强哈希方法)*/
//	public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	/** 超级管理员账号名称*/
	public static final String SUPER_ADMIN = "superAdmin";
	/** 超级管理员账号角色*/
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	/** 浏览角色*/
	public static final String ROLE_BROWSE = "ROLE_BROWSE";
	/** 登陆用户对象在session中的key*/
	public static final String LOGIN_USER_KEY = "guoyaoAccount";
	/** 游客*/
	public static final String AUTH_BROWSER = "游客";
	
	/** dwz主窗口标签ID*/
	protected String tabId = "";
	/** 数据类型*/
	protected final String jsonType = "application/json;charset=UTF-8";
	/** 重定向*/
	protected final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	/** 用户服务*/
	@Autowired
	protected UserService userService;
	/** 社交服务*/
	@Autowired
	protected UserConnectionService userConnectionService;
	
	/** 角色服务*/
	@Autowired
	protected RoleService roleService;
	/** 权限服务*/
	@Autowired
	protected PermissionService permissionService;
	/** 菜单服务*/
	@Autowired
	protected MenuService menuService;
	/** 日志服务*/
	@Autowired
	protected LogService logService;
	
	/** 加密 ||密码加密规则(BCrypt强哈希方法)*/
	@Autowired
	protected PasswordEncoder passwordEncoder;
	@Autowired
	protected SecurityProperties securityProperties;
	/** qq weixin social user 注册*/
//	@Autowired
//	protected AppSingUpUtils appSingUpUtils;
	@Autowired
	protected ProviderSignInUtils providerSignInUtils;
	
	protected void dwzResponse(DWZResponse dwz,HttpServletResponse response) throws IOException {
		response.setContentType(jsonType);
		response.getWriter().print(JSONObject.toJSONString(dwz));
	}
}
