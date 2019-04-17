/**
 * 
 */
package com.guoyao.auth.authorize.web.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuchao
 * @Date 【2019年1月17日:上午11:41:22】
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LogForm {
//	@ApiModelProperty(value = "日志ID")
	private Long id;
	/** 用户名*/
//	@ApiModelProperty(value = "用户名")
	private String username;
	/** 用户操作*/
//	@ApiModelProperty(value = "用户操作")
	private String operation;
	/** 请求方法*/
//	@ApiModelProperty(value = "请求方法")
	private String method;
	/** 请求参数*/
//	@ApiModelProperty(value = "请求参数")
	private String params;
	/** 执行时长(毫秒)*/
//	@ApiModelProperty(value = "访问服务耗时(毫秒)")
	private Long time;
	/** IP地址*/
//	@ApiModelProperty(value = "操作者IP")
	private String ip;
	/** 创建时间*/
//	@ApiModelProperty(value = "日志创建时间")
	@JsonFormat
	private Date createTime;
}
