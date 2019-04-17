package com.guoyao.auth.authorize.web.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.guoyao.auth.authorize.web.form.UserForm.FourLevellView;
import com.guoyao.auth.authorize.web.form.UserForm.OneLevelView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserConnectionForm {

	@NotBlank(message = "用户账号不能为空")
	@Length(min = 6, max = 50, message = "账号长度6~50位")
	@Pattern(regexp="^[A-Za-z]{1}\\w+",message="用户账号必须以字母开始的字母数字混合")
	@JsonView(OneLevelView.class)
	private String username;
	
	@NotBlank(message = "密码不能为空")
	@Length(min = 6, max = 50, message = "密码长度至少6位,至多50位")
	@Pattern(regexp="^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]+$",message="密码必须含有字母与数字组合!")
//	@ApiModelProperty(value = "用户密码")
	@JsonView(FourLevellView.class)
	private String password;
}
