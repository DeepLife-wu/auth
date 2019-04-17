/**
 * 
 */
package com.guoyao.auth.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**校验码生成器
 * @author wuchao
 * @Date 【2019年2月13日:下午3:43:12】
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 * @param request
	 * @return
	 */
	ValidateCode generate(ServletWebRequest request);
}
