/**
 * 
 */
package com.guoyao.auth.authorize.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wuchao
 * @Date 【2019年1月16日:上午11:46:23】
 */
@Getter
@AllArgsConstructor
public enum UserStatus implements CodeEnum {
	LOCK(0,"锁定"),
	ENABLE(1,"启用");
	
	private Integer code;
	private String message;
}
