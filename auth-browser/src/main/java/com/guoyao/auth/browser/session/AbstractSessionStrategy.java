/**
 * 
 */
package com.guoyao.auth.browser.session;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guoyao.auth.core.properties.SecurityProperties;
import com.guoyao.auth.core.support.SimpleResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 抽象的session失效处理器
 * @author wuchao
 * @Date 【2019年2月14日:上午9:34:08】
 */
@Slf4j
public abstract class AbstractSessionStrategy {

	/**
	 * 跳转的url
	 */
	private String destinationUrl;
	/**
	 * 系统配置信息
	 */
	private SecurityProperties securityPropertie;
	/**
	 * 重定向策略
	 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	/**
	 * 跳转前是否创建新的session
	 */
	private boolean createNewSession = true;

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @param invalidSessionUrl
	 * @param invalidSessionHtmlUrl
	 */
	public AbstractSessionStrategy(SecurityProperties securityPropertie) {
		String invalidSessionUrl = securityPropertie.getBrowser().getSession().getSessionInvalidUrl();
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
		Assert.isTrue(StringUtils.endsWithIgnoreCase(invalidSessionUrl, ".html"), "url must end with '.html'");
		this.destinationUrl = invalidSessionUrl;
		this.securityPropertie = securityPropertie;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.session.InvalidSessionStrategy#
	 * onInvalidSessionDetected(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("session失效");
		
		if (createNewSession) {
			request.getSession();
		}

		String sourceUrl = request.getRequestURI();
		String targetUrl;
		if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
			if(StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSignInPage())
					|| StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSignOutUrl())){
				targetUrl = sourceUrl;
			}else{
				targetUrl = destinationUrl;
			}
			log.info("跳转到:"+targetUrl);
			redirectStrategy.sendRedirect(request, response, targetUrl);
		} else {
			Object result = buildResponseContent(request);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(result));
		}

	}

	/**
	 * @param request
	 * @return
	 */
	protected Object buildResponseContent(HttpServletRequest request) {
		String message = "session已失效";
		if (isConcurrency()) {
			message = message + "，有可能是并发登录导致的";
		}
		return new SimpleResponse(message);
	}

	/**
	 * session失效是否是并发导致的
	 * 
	 * @return
	 */
	protected boolean isConcurrency() {
		return false;
	}

	/**
	 * Determines whether a new session should be created before redirecting (to
	 * avoid possible looping issues where the same session ID is sent with the
	 * redirected request). Alternatively, ensure that the configured URL does
	 * not pass through the {@code SessionManagementFilter}.
	 *
	 * @param createNewSession
	 *            defaults to {@code true}.
	 */
	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}
}
