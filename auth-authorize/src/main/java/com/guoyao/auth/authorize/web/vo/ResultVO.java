package com.guoyao.auth.authorize.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.guoyao.auth.authorize.web.consts.ServerCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO {
	/** 错误码.*/
    private Integer code;
    /** 提示信息*/
    private String message;
    /** 具体内容*/
    private Object data;
    
    public static ResultVO success(Object object) {
    	return ResultVO.builder().code(ServerCode.REQUEST_EXECUTE_OK.getCode())
    							 .message(ServerCode.REQUEST_EXECUTE_OK.getMessage())
    							 .data(object).build();
    }
    
    public static ResultVO success() {
    	return success(null);
    }
    
    public static ResultVO error(Integer code,String message) {
    	return ResultVO.builder().code(code).message(message).build();
    }
    
    public static ResultVO error(ServerCode server) {
    	return ResultVO.builder().code(server.getCode()).message(server.getMessage()).build();
    }
}