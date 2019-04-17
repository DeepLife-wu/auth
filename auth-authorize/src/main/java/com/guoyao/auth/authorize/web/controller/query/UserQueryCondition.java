/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.query;

import lombok.Data;
import lombok.ToString;

/**用户查询条件
 * @author wuchao
 * @Date 【2019年1月18日:下午3:48:39】
 */
@Data
@ToString
public class UserQueryCondition {
//	@ApiModelProperty(value = "用户账号")
	private String username;
	
//	@ApiModelProperty(value = "用户年龄值")
	private Integer age;
	
//	@ApiModelProperty(value = "用户性别(0男,1女)")
	private Integer gender;
	
//	@ApiModelProperty(value = "用户邮箱")
	private String email;
	
//	@ApiModelProperty(value = "用户手机号")
	private String phone;
	
//	@ApiModelProperty(value = "用户状态(0锁定,1启用)")
	private Integer status;
}
