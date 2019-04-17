/**
 * 
 */
package com.guoyao.auth.authorize.init;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.guoyao.auth.authorize.model.Menu;
import com.guoyao.auth.authorize.model.Permission;
import com.guoyao.auth.authorize.model.Role;
import com.guoyao.auth.authorize.model.User;
import com.guoyao.auth.authorize.model.enums.Gender;
import com.guoyao.auth.authorize.model.enums.HttpMethod;
import com.guoyao.auth.authorize.model.enums.LinkType;
import com.guoyao.auth.authorize.model.enums.UserStatus;
import com.guoyao.auth.authorize.repository.MenuRepository;
import com.guoyao.auth.authorize.repository.PermissionRepository;
import com.guoyao.auth.authorize.repository.RoleRepository;
import com.guoyao.auth.authorize.repository.UserRepository;
import com.guoyao.auth.authorize.web.controller.AbstractController;

import lombok.extern.slf4j.Slf4j;

/**用户数据生成器
 * @author wuchao
 * @Date 【2019年1月24日:下午2:38:03】
 */
@Slf4j
@Component
public class UserDataGenerator extends AbstractDataGenerator {
	/** 用户服务*/
	@Autowired
	protected UserRepository userRepository;
	/** 角色服务*/
	@Autowired
	protected RoleRepository roleRepository;
	/** 资源服务*/
	@Autowired
	protected PermissionRepository permissionRepository;
	/** 菜单服务*/
	@Autowired
	private MenuRepository menuRepository;
	/** 加密*/
	@Autowired
	private PasswordEncoder passwordEncoder;

	/* (non-Javadoc)
	 * @see com.guoyao.auth.authorize.init.DataGenerator#getIndex()
	 */
	@Override
	public Integer getIndex() {
		return Integer.MIN_VALUE;
	}

	/* (non-Javadoc)
	 * @see com.guoyao.auth.authorize.init.AbstractDataGenerator#init()
	 */
	@Override
	protected void init() throws Exception {
		savePermission();
		saveRole();
		saveUser();
		saveMenu();
	}


	/* (non-Javadoc)
	 * @see com.guoyao.auth.authorize.init.AbstractDataGenerator#isNeed()
	 */
	@Override
	protected boolean isNeed() {
		return userRepository.count() == 0;
	}
	
	private void saveMenu() {
		if(CollectionUtils.isNotEmpty(menuList)) {
			for(Menu menu : menuList) {
				menuRepository.save(menu);
				if(menu.getParentId() == null && "系统管理".equals(menu.getName())) {
					List<Menu> twoMenuList = Arrays.asList(
							Menu.builder().parentId(menu.getId()).name("用户管理").menuUrl("/dwz/user/list").sort(1).createTime(new Date()).build(),
							Menu.builder().parentId(menu.getId()).name("角色管理").menuUrl("/dwz/role/list").sort(2).createTime(new Date()).build(),
							Menu.builder().parentId(menu.getId()).name("资源管理").menuUrl("/dwz/permission/list").sort(3).createTime(new Date()).build(),
							Menu.builder().parentId(menu.getId()).name("菜单管理").menuUrl("/dwz/menu/list").sort(4).createTime(new Date()).build(),
							Menu.builder().parentId(menu.getId()).name("日志管理").menuUrl("/dwz/log/list").sort(5).createTime(new Date()).build()
							);
					for(Menu m : twoMenuList) {
						menuRepository.save(m);
					}
					log.info("二级菜单共{}条数据成功入库",twoMenuList.size());
				}
			}
			log.info("一级菜单共{}条数据成功入库",menuList.size());
		}
	}
	
	private void saveUser() {
		if(CollectionUtils.isNotEmpty(userList)) {
			for(User user : userList) {
				if(user.getUsername().equals(AbstractController.SUPER_ADMIN)) {
					user.setPassword(passwordEncoder.encode("123456"));
				}
				if(CollectionUtils.isNotEmpty(roleList)) {
					for(Role role : roleList) {
						if(AbstractController.ROLE_ADMIN.equals(role.getCode())
								&& CollectionUtils.isNotEmpty(role.getPermissions())) {
							user.getRoles().add(role);
						}
					}
				}
				userRepository.save(user);
			}
			log.info("用户共{}条数据成功入库",userList.size());
		}
	}
	
	private void saveRole() {
		//角色数据入库
		if(roleRepository.count() == 0) {
			for(Role role : roleList) {
				if(AbstractController.ROLE_ADMIN.equals(role.getCode())) {
					for(Permission p : permissionList) {
						role.getPermissions().add(p);
					}
				}
				if(AbstractController.ROLE_BROWSE.equals(role.getCode())) {
					for(Permission p : permissionList) {
						if((p.getRequestMethod().equals(HttpMethod.GET.getCode()) && p.getType().equals(LinkType.MENU.getCode()))
								|| (StringUtils.equalsIgnoreCase("/index", p.getUrl()))	
						) {
							role.getPermissions().add(p);
						}
					}
				}
				roleRepository.save(role);
			}
			log.info("角色共{}条数据成功入库",roleList.size());
		}
	}
	
	private void savePermission() {
		//资源数据入库
		if(permissionRepository.count() == 0) {
			for(Permission permission : permissionList) {
				permissionRepository.save(permission);
			}
			log.info("资源共{}条数据成功入库",permissionList.size());
		}
	}
	
	private List<Menu> menuList = Arrays.asList(
			Menu.builder().name("系统管理").menuUrl("javascript:;").sort(1).createTime(new Date()).build()
			);
	
	private List<User> userList = Arrays.asList(
			User.builder().name("超级管理员").username(AbstractController.SUPER_ADMIN).password("")
						  .email("15801630979@163.com").phone("15801630979").age(30).gender(Gender.MALE.getCode())
						  .birthday(new Date()).status(UserStatus.ENABLE.getCode()).operator("UserDataGenerator")
						  .operateIp("localhost").createTime(new Date()).updateTime(new Date()).lastLoginTime(new Date())
						  .loginCount(0L).build()
			);
	
	private List<Role> roleList = Arrays.asList(
			Role.builder().code(AbstractController.ROLE_ADMIN).name("超级管理员").createTime(new Date()).updateTime(new Date()).build(),
			Role.builder().code(AbstractController.ROLE_BROWSE).name("浏览").createTime(new Date()).updateTime(new Date()).build()
			);
	
	private List<Permission> permissionList = Arrays.asList(
			//首页
			Permission.builder().name("首页").url("/index").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			//退出(拦截core封装好的signOut功能)进行处理
//			Permission.builder().name("退出").url("/logout").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			//日志
			Permission.builder().name("日志管理").url("/dwz/log/list").requestMethod(HttpMethod.GET.getCode()).type(LinkType.MENU.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			/** 一级菜单管理模块下的资源*/
			//页面
			Permission.builder().name("添加一级菜单页面").url("/dwz/menu/add").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("修改一级菜单页面").url("/dwz/menu/update").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("菜单管理").url("/dwz/menu/list").requestMethod(HttpMethod.GET.getCode()).type(LinkType.MENU.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			//rest 风格处理
			Permission.builder().name("添加一级菜单").url("/dwz/menu/add").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("修改一级菜单").url("/dwz/menu/update").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("删除一级菜单").url("/dwz/menu/delete").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			/** 二级菜单管理模块下的资源*/
			//页面
			Permission.builder().name("添加二级菜单页面").url("/dwz/menu/child/add").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("修改二级菜单页面").url("/dwz/menu/child/update").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("子菜单管理").url("/dwz/menu/child/list").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			//rest 风格处理
			Permission.builder().name("添加二级菜单").url("/dwz/menu/child/add").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("修改二级菜单").url("/dwz/menu/child/update").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("删除二级菜单").url("/dwz/menu/child/delete").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			/** 用户管理模块下的资源*/
			//页面
			Permission.builder().name("添加用户页面").url("/dwz/user/add").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("修改用户页面").url("/dwz/user/update").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("用户授权页面").url("/dwz/user/grant").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("用户密码页面").url("/dwz/user/password").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("用户管理").url("/dwz/user/list").requestMethod(HttpMethod.GET.getCode()).type(LinkType.MENU.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			//普通链接处理
			Permission.builder().name("修改密码").url("/dwz/user/password").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("授权用户").url("/dwz/user/grant").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("检查用户账号是否占用").url("/dwz/user/check").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			//rest 风格处理
			Permission.builder().name("添加用户").url("/dwz/user/add").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("修改用户").url("/dwz/user/update").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("删除用户").url("/dwz/user/delete").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			/** 角色管理模块下的资源*/
			//页面
			Permission.builder().name("添加角色页面").url("/dwz/role/add").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("修改角色页面").url("/dwz/role/update").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("角色授权页面").url("/dwz/role/grant").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("角色管理").url("/dwz/role/list").requestMethod(HttpMethod.GET.getCode()).type(LinkType.MENU.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			//普通链接处理
			Permission.builder().name("授权角色").url("/dwz/role/grant").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			//基本处理
			Permission.builder().name("添加角色").url("/dwz/role/add").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("修改角色").url("/dwz/role/update").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("删除角色").url("/dwz/role/delete").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			/** 资源管理模块下的资源*/
			//页面
			Permission.builder().name("资源添加页面").url("/dwz/permission/add").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("资源修改页面").url("/dwz/permission/update").requestMethod(HttpMethod.GET.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("资源管理").url("/dwz/permission/list").requestMethod(HttpMethod.GET.getCode()).type(LinkType.MENU.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			
			//处理
			Permission.builder().name("添加资源").url("/dwz/permission/add").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("修改资源").url("/dwz/permission/update").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build(),
			Permission.builder().name("删除资源").url("/dwz/permission/delete").requestMethod(HttpMethod.POST.getCode()).type(LinkType.BUTTON.getCode()).createTime(new Date()).updateTime(new Date()).build()
			);
}
