/**
 * 
 */
package com.guoyao.auth.core.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**简单响应的封装类
 * @author wuchao
 * @Date 【2019年2月13日:下午5:46:32】
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResponse {
	private Object content;
}
