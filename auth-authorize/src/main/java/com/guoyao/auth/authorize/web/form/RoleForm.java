/**
 * 
 */
package com.guoyao.auth.authorize.web.form;

import java.util.Date;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

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
public class RoleForm {

	@Null(message="id必须为空")
//	@ApiModelProperty(value = "角色id")
	private Long id;
	
	@NotBlank(message = "角色码不能为空")
//	@ApiModelProperty(value = "角色码")
	private String code;

	@NotBlank(message = "角色中文名称不能为空")
	@Pattern(regexp="^[\\u4E00-\\u9FA5][\\\\w\\u4E00-\\u9FA5]{1,20}",message="请输入合法的角色中文名称")
//	@ApiModelProperty(value = "角色中文名称")
	private String name;
	
	@JsonFormat
//	@ApiModelProperty(value = "角色创建时间")
	private Date createTime;

	@JsonFormat
//	@ApiModelProperty(value = "角色更新时间")
	private Date updateTime;
}
