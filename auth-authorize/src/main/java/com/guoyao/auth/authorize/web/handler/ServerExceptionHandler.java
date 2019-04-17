/**
 * 
 */
package com.guoyao.auth.authorize.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.guoyao.auth.authorize.exception.DwzDataException;
import com.guoyao.auth.authorize.exception.RestDataException;
import com.guoyao.auth.authorize.web.consts.ServerCode;
import com.guoyao.auth.authorize.web.dwz.DWZResponse;
import com.guoyao.auth.authorize.web.vo.ResultVO;

/**服务异常捕获
 * @author wuchao
 * @Date 【2019年1月17日:下午4:20:06】
 */
@ControllerAdvice
public class ServerExceptionHandler {
	
	//dwz界面捕获异常
	@ExceptionHandler(value = DwzDataException.class)
	@ResponseBody
	public DWZResponse handlerDataException(DwzDataException e) {
		return DWZResponse.builder().statusCode(e.getCode()).message(e.getMessage()).build();
	}

	//rest json调用捕获异常
	@ExceptionHandler(value = RestDataException.class)
	@ResponseBody
	public ResultVO handlerDataException(RestDataException e) {
		return ResultVO.error(e.getCode(), e.getMessage());
	}
	
	//捕获系统总异常
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResultVO handlerException(Exception e) {
		e.printStackTrace();
		return ResultVO.error(ServerCode.REQUEST_PARAMETER_ERROR.getCode(), e.getMessage());
	}
}
