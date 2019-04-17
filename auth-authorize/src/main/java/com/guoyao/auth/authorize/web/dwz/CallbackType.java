/**
 * 
 */
package com.guoyao.auth.authorize.web.dwz;

/**RESTful风格的服务端写dwz工具类感觉不是很实用
 * @author wuchao
 * @Date 【2019年1月28日:下午4:39:19】
 */
public interface CallbackType {
	/** 关闭当前tab*/
	String closeCurrent = "closeCurrent";
	/** 需要forwardUrl值*/
	String forward = "forward";
	String forwardConfirm = "forwardConfirm";
}
