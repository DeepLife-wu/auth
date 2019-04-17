/**
 * 
 */
package com.guoyao.auth.authorize.authentication;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wuchao
 * @Date 【2019年2月15日:上午9:47:17】
 */
@Data
@AllArgsConstructor
public class AuthGrantedAuthority implements GrantedAuthority {
	private String url;
	private String method;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
//		return this.url + ";" + this.method;
		return this.url;
	}
}
