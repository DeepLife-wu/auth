/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

/**用户查询条件
 * @author wuchao
 * @Date 【2019年1月18日:下午3:48:39】
 */
@Data
@ToString
public class LogQueryCondition {
	/** 用户名*/
//	@ApiModelProperty(value = "用户账号")
	private String username;
	/** 用户操作*/
//	@ApiModelProperty(value = "用户操作")
	private String operation;
	/** 开始时间*/
	@JsonFormat
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	@ApiModelProperty(value = "开始时间(默认1周前的0点0分0秒)")
	private Date startTime;
//	private String startTime;
	
	/** 结束时间*/
	@JsonFormat
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	@ApiModelProperty(value = "结束时间(默认今天的23点59分59秒)")
	private Date endTime;
//	private String endTime;
}
