/**
 * 
 */
package com.guoyao.auth.authorize.web.dwz;

import com.guoyao.auth.authorize.model.enums.CodeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**RESTful风格的服务端写dwz工具类感觉不是很实用
 * @author wuchao
 * @Date 【2019年1月28日:下午4:24:34】
 */
@Getter
@AllArgsConstructor
public enum DwzCode implements CodeEnum {

	OK(200,"操作成功"),
	ERROR(300,"操作失败"),
	TIMEOUT(301,"会话超时");
	
	private Integer code;
	private String message;
}
