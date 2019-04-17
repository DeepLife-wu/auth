package com.guoyao.auth.authorize.web.consts;

import com.guoyao.auth.authorize.model.enums.CodeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统服务码
 * @author wuchao
 * @Date 【2019年1月17日:下午3:45:44】
 */
@Getter
@AllArgsConstructor
public enum ServerCode implements CodeEnum {
	/** 标准码*/
	REQUEST_EXECUTE_OK(200,"请求执行成功"),
	REQUEST_PARAMETER_ERROR(500,"请求参数错误!"),
	
	/** 自定义码*/
	PC_DATA_NOT_EXIST(20190101,"数据不存在"),
	PC_MENU_HAS_CHILD_DATA(20190102,"菜单下有子菜单"),
	PC_ID_LIST_UNCHECK(20190103,"数据列表未选中"),
	PC_USER_ALREADY_EXISTS(20190104,"用户已存在"),
	PC_USER_NOT_EXISTS(20190105,"用户不存在"),
	PC_USER_PASSWORD_MISTAKE(20190106,"用户密码不正确"),
	PC_USER_ALREADY_BINDING(20190107,"用户账号已绑定社交账号"),
	
	/** 非权限管理的资源码值*/
	BOOK_TYPE_NOT_EXIST(20200101,"书籍类型不存在"),
	BOOK_CHAPTER_NOT_EXIST(20200102,"书籍章节不存在"),
	BOOK_SEARCH_CONDITION_NOT_EMPTY(20200103,"书籍查询条件不能为空");
	;
	
	private Integer code;
	private String message;
}
