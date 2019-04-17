/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.freemark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import com.guoyao.auth.authorize.annotation.SysLog;
import com.guoyao.auth.authorize.exception.RestDataException;
import com.guoyao.auth.authorize.model.Role;
import com.guoyao.auth.authorize.model.User;
import com.guoyao.auth.authorize.model.UserConnection;
import com.guoyao.auth.authorize.model.enums.UserStatus;
import com.guoyao.auth.authorize.web.consts.ServerCode;
import com.guoyao.auth.authorize.web.controller.AbstractController;
import com.guoyao.auth.authorize.web.controller.converter.UserConverter;
import com.guoyao.auth.authorize.web.form.MenuForm;
import com.guoyao.auth.authorize.web.form.UserConnectionForm;
import com.guoyao.auth.authorize.web.form.UserForm;
import com.guoyao.auth.authorize.web.util.RequestHolder;
import com.guoyao.auth.authorize.web.util.SecurityUtils;
import com.guoyao.auth.authorize.web.vo.ResultVO;
import com.guoyao.auth.core.properties.SecurityConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月25日:下午4:51:28】
 */
@Slf4j
@Controller
public class LoginController extends AbstractController {
	public static final String AUTH_INDEX_VIEW = "/dwz/index";

	@RequestMapping("/")
	public String root(Model model) {
		return index(model);
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		if(!RequestHolder.isLogin()) { 
			User user = userService.findByUsername(SecurityUtils.getLoginName());
			RequestHolder.setCurrentUser(user);
			Long loginCount = user.getLoginCount(); 
			loginCount = loginCount == null ? 0L : loginCount;
			loginCount++;
			user.setLoginCount(loginCount);
			user.setLastLoginTime(new Date());
			userService.save(user);
			log.info("欢迎{}登录安全系统,登录次数{}",user.getUsername(),loginCount);
		}
		List<MenuForm> menuList = menuService.findRootMenu();
		if(CollectionUtils.isNotEmpty(menuList)) {
			List<String> permissionUrls = SecurityUtils.getUserAuthorities();
			for(MenuForm menu : menuList) {
				List<MenuForm> childrenList = menu.getChildrenList();
				if(CollectionUtils.isNotEmpty(childrenList)) {
					List<MenuForm> loopChild = new ArrayList<>(childrenList);
					for(MenuForm child : loopChild) {
						if(!permissionUrls.contains(child.getMenuUrl()))
							childrenList.remove(child);
					}
				}
			}
		}
		model.addAttribute("menuList", menuList);
		return AUTH_INDEX_VIEW;
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		//退出之前删除session中的对象
		RequestHolder.removeCurrentUser();
		response.sendRedirect(SecurityConstants.DEFAULT_LOGOUT_URL);
	}
	
	@SysLog("社交用户注册或绑定")
	@PostMapping("/user/regist")
	@ResponseBody
	public Object regist(String type, @Valid UserConnectionForm userConnectionForm/*User user*/,BindingResult errors , HttpServletRequest request,HttpServletResponse response) throws IOException {
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		String userId = userConnectionForm.getUsername();
		User dbUser = userService.findByUsername(userId);
		if(StringUtils.equalsIgnoreCase(type, SocialUserOperation.REGIST)) {
			if(dbUser != null) {
				throw new RestDataException(ServerCode.PC_USER_ALREADY_EXISTS);
			}
			providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
			UserConnection userConnection = userConnectionService.findByUserId(userId);
			
			UserForm userForm = UserForm.builder().username(userConnectionForm.getUsername()).password(userConnectionForm.getPassword()).status(UserStatus.ENABLE.getCode()).build();
			User user = UserConverter.form2EntityCreate(userForm);
			String password = passwordEncoder.encode(user.getPassword());
			user.setPassword(password);
			user.setUserConnection(userConnection);
			Role role = roleService.findByCode(AbstractController.ROLE_BROWSE);
			user.getRoles().add(role);
			userService.save(user);
		} else if(StringUtils.equalsIgnoreCase(type, SocialUserOperation.BINDING)) {
			if(dbUser == null) {
				throw new RestDataException(ServerCode.PC_USER_NOT_EXISTS);
			}
			if(!passwordEncoder.matches(userConnectionForm.getPassword(), dbUser.getPassword())) {
				throw new RestDataException(ServerCode.PC_USER_PASSWORD_MISTAKE); 
			}
			if(dbUser.getUserConnection() != null) {
				throw new RestDataException(ServerCode.PC_USER_ALREADY_BINDING);
			}
			providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
			UserConnection userConnection = userConnectionService.findByUserId(userId);
			dbUser.setUserConnection(userConnection);
			userService.save(dbUser);
		}
//		appSingUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
		return ResultVO.success();		
	}
	
	public interface SocialUserOperation {
		/** 注册*/
		String REGIST = "regist";
		/** 绑定*/
		String BINDING = "binding";
	}
}
