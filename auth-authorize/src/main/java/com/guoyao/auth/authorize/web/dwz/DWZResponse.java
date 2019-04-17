/**
 * 
 */
package com.guoyao.auth.authorize.web.dwz;

import lombok.Builder;
import lombok.Data;

/**RESTful风格的服务端写dwz工具类感觉不是很实用
 * @author wuchao
 * @Date 【2019年1月28日:下午4:44:55】
 */
@Builder
@Data
public class DWZResponse {
	/** dwz框架需要的响应码{@link com.guoyao.auth.authorize.web.dwz.DwzCode}*/
	public  Integer statusCode;
	/** dwz响应消息*/
	private String message;
	/** dwz标签页id*/
	private String navTabId;
	/** 标识此弹出层的 ID*/
	private String rel;
	/** 服务端响应dwz的回调类型*/
	private String callbackType;
	/** dwz跳转地址*/
	private String forwardUrl;
	/** 带回前端数据结果对象*/
	private Object result;
}
