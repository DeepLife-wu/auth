/**
 * 
 */
package com.guoyao.auth.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.guoyao.auth.core.properties.SecurityProperties;

/**
 * 并发登录导致session失效时，默认的处理策略
 * @author wuchao
 * @Date 【2019年2月14日:上午9:36:12】
 */
public class GuoyaoExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

	public GuoyaoExpiredSessionStrategy(SecurityProperties securityPropertie) {
		super(securityPropertie);
	}

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		onSessionInvalid(event.getRequest(), event.getResponse());
	}
	
	@Override
	protected boolean isConcurrency() {
		return true;
	}
}
