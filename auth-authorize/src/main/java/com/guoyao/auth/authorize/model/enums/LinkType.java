/**
 * 
 */
package com.guoyao.auth.authorize.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**资源类型
 * @author wuchao
 * @Date 【2019年1月16日:上午11:02:43】
 */
@Getter
@AllArgsConstructor
public enum LinkType implements CodeEnum {
	/** 按钮*/
	BUTTON(0,"按钮"),
	/** 菜单*/
	MENU(1,"菜单");
	
	private Integer code;
	private String message;
}
