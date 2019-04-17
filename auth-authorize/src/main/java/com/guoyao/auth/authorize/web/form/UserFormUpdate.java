/**
 * 
 */
package com.guoyao.auth.authorize.web.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guoyao.auth.authorize.model.enums.Gender;
import com.guoyao.auth.authorize.model.enums.UserStatus;
import com.guoyao.auth.authorize.web.util.EnumUtil;

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
public class UserFormUpdate {
	@Null(message="id必须为空")
//	@ApiModelProperty(value = "用户id")
	private Long id;

	@NotBlank(message = "用户中文名称不能为空")
	@Pattern(regexp="^[\\u4E00-\\u9FA5][\\\\w\\u4E00-\\u9FA5]{1,20}",message="请输入合法的中文名称")
//	@ApiModelProperty(value = "用户中文名称")
//	@MyConstraint(message = "这是一个测试")
	private String name;

	@NotBlank(message = "用户账号不能为空")
	@Length(min = 6, max = 50, message = "账号长度6~50位")
	@Pattern(regexp="^[A-Za-z]{1}\\w+",message="用户账号必须以字母开始的字母数字混合")
//	@ApiModelProperty(value = "用户账号")
	private String username;
	
//	@Min(value=6,message="")
	@NotNull(message = "用户年龄不能为空")
	@Range(min=1,max=100,message="年龄范围1~100")
//	@ApiModelProperty(value = "用户年龄")
	private Integer age;
	
	@NotNull(message = "用户性别不能为空")
	@Range(min=0,max=1,message="用户性别(0男,1女)")
//	@ApiModelProperty(value = "用户性别(0男,1女)")
	private Integer gender;
	
	@NotNull(message = "用户生日不能为空")
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
//	@ApiModelProperty(value = "用户出生日期")
	private Date birthday;
	
	@Pattern(regexp="([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$",message="邮箱格式不正确")
//	@ApiModelProperty(value = "用户邮箱")
	private String email;
	
	@Pattern(regexp="^[0-9]{11}$",message="手机号格式不正确")
//	@ApiModelProperty(value = "用户手机号")
	private String phone;
	
	@Range(min=0,max=1,message="用户状态(0锁定,1启用)")
//	@ApiModelProperty(value = "用户状态(0锁定,1启用)")
	private Integer status = UserStatus.ENABLE.getCode();
	
//	@ApiModelProperty(value = "操作人的账号")
	private String operator;
	
//	@ApiModelProperty(value = "操作用户的IP")
	private String operateIp;
	
//	@ApiModelProperty(value = "用户最后一次登录的时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	
//	@ApiModelProperty(value = "用户创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

//	@ApiModelProperty(value = "用户更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
//	@ApiModelProperty(value = "用户登录系统的次数")
	private Long loginCount;
	
	@JsonIgnore
	public Gender getGenderEnum() {
		return EnumUtil.getByCode(gender,Gender.class);
	}

	@JsonIgnore
	public UserStatus getUserStatusEnum() {
		return EnumUtil.getByCode(status,UserStatus.class);
	}
}
