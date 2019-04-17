/**
 * 
 */
package com.guoyao.auth.authorize.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wuchao
 * @Date 【2019年1月17日:下午1:19:36】
 */
@Getter
@AllArgsConstructor
public enum Gender implements CodeEnum {
	MALE(0,"男性"),
	FEMALE(1,"女性"),
	UNKNOWN(2,"不详");

	private Integer code;
	private String message;
}
