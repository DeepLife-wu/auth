/**
 * 
 */
package com.guoyao.auth.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**校验码处理器，封装不同校验码的处理逻辑
 * @author wuchao
 * @Date 【2019年2月13日:下午3:43:42】
 */
public interface ValidateCodeProcessor {

	/**
	 * 创建校验码
	 * 
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 * 
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);

}
