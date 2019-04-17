/**
 * 
 */
package com.guoyao.auth.authorize.web.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

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
public class UserPasswordForm {
	@NotBlank(message = "密码不能为空")
	@Length(min = 6, max = 50, message = "密码长度至少6位,至多50位")
	@Pattern(regexp="^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]+$",message="密码必须含有字母与数字组合!")
//	@ApiModelProperty(value = "用户密码")
	private String password;
}
