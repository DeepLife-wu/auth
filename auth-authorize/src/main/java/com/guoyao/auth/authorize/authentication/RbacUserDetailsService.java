/**
 * 
 */
package com.guoyao.auth.authorize.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.guoyao.auth.authorize.model.Permission;
import com.guoyao.auth.authorize.model.Role;
import com.guoyao.auth.authorize.model.User;
import com.guoyao.auth.authorize.model.enums.HttpMethod;
import com.guoyao.auth.authorize.model.enums.UserStatus;
import com.guoyao.auth.authorize.service.UserService;
import com.guoyao.auth.authorize.web.util.EnumUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年2月14日:上午11:41:23】
 */
@Slf4j
@Component
@Transactional
public class RbacUserDetailsService implements UserDetailsService,SocialUserDetailsService {
	@Autowired
	private UserService userService;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("表单登录用户名:" + username);
		return buildUser(username);
	}

	private SocialUserDetails buildUser(String username) {
		boolean enabled = true;                		//用户帐号是否已启用
        boolean accountNonExpired = true;       	//是否过期
        boolean credentialsNonExpired = true;  		//用户凭证是否已经过期
        boolean accountNonLocked = true; 			//是否锁定 
		User user = userService.findByAccount(username);
		if(user == null ) {
			enabled = false;
			throw new UsernameNotFoundException("用户不存在");
		} else if ( user.getStatus() == UserStatus.LOCK.getCode() ) {
			accountNonLocked = false;
		}
		
		Collection<GrantedAuthority> authorities = getAuthorities(user);
		SocialUserDetails userDetails = new SocialUser(
				user.getUsername(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);
		return userDetails;
	}
	
	private Collection<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Set<Role> roles = user.getRoles();
		if(CollectionUtils.isNotEmpty(roles)) {
			for(Role role : roles) {
				GrantedAuthority rga = new SimpleGrantedAuthority(role.getCode());
				authorities.add(rga);
				
				Set<Permission> permissions = role.getPermissions();
				if(CollectionUtils.isNotEmpty(permissions)) {
					for(Permission p : permissions) {
//						GrantedAuthority pga = new SimpleGrantedAuthority(p.getUrl());
						HttpMethod httpMethod = EnumUtil.getByCode(p.getRequestMethod(),HttpMethod.class);
						AuthGrantedAuthority paga = new AuthGrantedAuthority(p.getUrl(), httpMethod.name());
						authorities.add(paga);
					}
				}
			}
		}
		return authorities;
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		log.info("社交账号登录用户id:" + userId);
		return buildUser(userId);
	}
}
