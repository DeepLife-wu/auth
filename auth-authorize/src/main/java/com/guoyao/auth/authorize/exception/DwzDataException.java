/**
 * 
 */
package com.guoyao.auth.authorize.exception;

import com.guoyao.auth.authorize.web.dwz.DwzCode;

import lombok.Data;

/**权限界面使用的dwz,当界面请求的参数数据有问题抛此异常
 * @author wuchao
 * @Date 【2019年1月18日:下午2:14:32】
 */
@Data
public class DwzDataException extends RuntimeException {
	
	private Integer code;
	private String message;
	
	public DwzDataException(Integer code,String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
}
