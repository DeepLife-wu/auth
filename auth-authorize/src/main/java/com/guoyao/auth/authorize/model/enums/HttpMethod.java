/**
 * 
 */
package com.guoyao.auth.authorize.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wuchao
 * @Date 【2019年1月22日:上午11:51:51】
 */
@Getter
@AllArgsConstructor
public enum HttpMethod implements CodeEnum {
	GET(0,"GET"), 
	HEAD(1,"HEAD"), 
	POST(2,"POST"), 
	PUT(3,"PUT"), 
	PATCH(4,"PATCH"), 
	DELETE(5,"DELETE"), 
	OPTIONS(6,"OPTIONS"), 
	TRACE(7,"TRACE");
	
	private Integer code;
	private String message;
}
