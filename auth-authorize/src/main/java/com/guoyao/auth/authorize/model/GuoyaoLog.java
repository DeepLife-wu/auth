/**
 * 
 */
package com.guoyao.auth.authorize.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wuchao
 * @Date 【2019年2月18日:下午2:49:02】
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "guoyao_log")
public class GuoyaoLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	/** 用户名*/
	private String username;
	/** 用户操作*/
	private String operation;
	/** 请求方法*/
	private String method;
	/** 请求参数*/
	private String params;
	/** 执行时长(毫秒)*/
	private Long time;
	/** IP地址*/
	private String ip;
	/** 创建时间*/
	private Date createTime;
}
