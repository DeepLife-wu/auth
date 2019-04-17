/**
 * 
 */
package com.guoyao.auth.core.social.weixin.api;

/**微信API调用接口
 * @author wuchao
 * @Date 【2019年2月13日:下午6:06:45】
 */
public interface Weixin {

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.security.social.api.SocialUserProfileService#getUserProfile(java.lang.String)
	 */
	WeixinUserInfo getUserInfo(String openId);
}
