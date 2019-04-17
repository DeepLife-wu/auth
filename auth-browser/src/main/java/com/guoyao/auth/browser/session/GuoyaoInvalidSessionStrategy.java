/**
 * 
 */
package com.guoyao.auth.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;

import com.guoyao.auth.core.properties.SecurityProperties;

/**
 * 默认的session失效处理策略
 * @author wuchao
 * @Date 【2019年2月14日:上午9:38:08】
 */
public class GuoyaoInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

	public GuoyaoInvalidSessionStrategy(SecurityProperties securityProperties) {
		super(securityProperties);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		onSessionInvalid(request, response);
	}
}
