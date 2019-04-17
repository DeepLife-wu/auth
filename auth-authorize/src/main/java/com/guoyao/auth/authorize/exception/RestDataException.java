/**
 * 
 */
package com.guoyao.auth.authorize.exception;

import com.guoyao.auth.authorize.web.consts.ServerCode;

import lombok.Data;

/**rest风格请求的数据出问题抛此异常
 * @author wuchao
 * @Date 【2019年1月18日:下午2:14:32】
 */
@Data
public class RestDataException extends RuntimeException {
	
	private Integer code;
	private String message;
	
	public RestDataException(ServerCode serverCode) {
		super(serverCode.getMessage());
		this.code = serverCode.getCode();
		this.message = serverCode.getMessage();
	}
	
	public RestDataException(Integer code,String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
}
