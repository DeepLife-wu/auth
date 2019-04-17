/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.converter;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.guoyao.auth.authorize.model.User;
import com.guoyao.auth.authorize.web.form.UserForm;
import com.guoyao.auth.authorize.web.form.UserFormUpdate;
import com.guoyao.auth.authorize.web.util.IpUtil;
import com.guoyao.auth.authorize.web.util.RequestHolder;
import com.guoyao.auth.authorize.web.util.SecurityUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年1月21日:下午3:21:37】
 */
@Slf4j
public class UserConverter {
	public static User form2EntityCreate(UserForm userForm) {
		log.info("form2EntityCreate user form {}",userForm);
		User user = User.builder().build();
		BeanUtils.copyProperties(userForm, user);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setOperator(RequestHolder.getCurrentUser().getUsername());
		user.setOperateIp(IpUtil.getIpAddr(RequestHolder.request()));
		log.info("form2EntityCreate user entity {}",user);
		return user;
	}
	
	/**
	 * 把form中的字段部分更新到permission中
	 * @param userForm
	 * @param user
	 */
	public static void form2EntityUpdate(UserFormUpdate userForm, User user) {
		log.info("form2EntityUpdate user form {}",userForm);
		user.setName(userForm.getName());
		user.setUsername(userForm.getUsername());
		//password
		user.setEmail(userForm.getEmail());
		user.setPhone(userForm.getPhone());
		user.setAge(userForm.getAge());
		user.setGender(userForm.getGender());
		user.setBirthday(userForm.getBirthday());
		user.setEmail(userForm.getEmail());
		user.setPhone(userForm.getPhone());
		user.setStatus(userForm.getStatus());
		user.setUpdateTime(new Date());
		log.info("form2EntityUpdate user entity {}",user);
	}
	
	public static UserForm entity2FormCreate(User user) {
		log.info("entity2FormCreate user entity {}",user);
		UserForm form = UserForm.builder().build();
		BeanUtils.copyProperties(user, form);
		form.setPassword(null);
		log.info("entity2FormCreate user form {}",form);
		return form;
	}

	/**
	 * 把entity中的字段部分更新到permission中
	 * @param permission
	 * @param permissionForm
	 */
	public static void entity2FormUpdate(User user, UserFormUpdate userForm) {
		log.info("entity2FormUpdate user entity {}",userForm);
		userForm.setId(user.getId());
		userForm.setUpdateTime(user.getUpdateTime());
		userForm.setCreateTime(user.getCreateTime());
		userForm.setLastLoginTime(user.getLastLoginTime());
		userForm.setLoginCount(user.getLoginCount());
		userForm.setOperator(user.getOperator());
		log.info("entity2FormUpdate user form {}",userForm);
	}
}
