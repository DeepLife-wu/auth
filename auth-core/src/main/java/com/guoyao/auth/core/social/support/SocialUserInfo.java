/**
 * 
 */
package com.guoyao.auth.core.social.support;

import lombok.Data;

/**
 * @author wuchao
 * @Date 【2019年2月13日:下午6:00:59】
 */
@Data
public class SocialUserInfo {
	private String providerId;
	private String providerUserId;
	private String nickname;
	private String headimg;
}
