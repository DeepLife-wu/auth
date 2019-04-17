/**
 * 
 */
package com.guoyao.auth.authorize.web.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guoyao.auth.authorize.model.enums.HttpMethod;
import com.guoyao.auth.authorize.model.enums.LinkType;
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
public class PermissionForm {
	
	@Null(message="id必须为空")
//	@ApiModelProperty(value = "资源id")
	private Long id;

	@NotBlank(message = "资源中文名称不能为空")
	@Pattern(regexp="^[\\u4E00-\\u9FA5][\\\\w\\u4E00-\\u9FA5]{1,20}",message="请输入合法的资源中文名称")
//	@ApiModelProperty(value = "资源中文名称")
	private String name;
	
	@NotBlank(message = "资源链接不能为空")
//	@ApiModelProperty(value = "资源链接地址")
	private String url;
	
	/**
	 * 资源请求方式:get ,post ,put , delete
	 */
	@NotNull(message = "资源请求方式不能为空")
//	@ApiModelProperty(value = "资源请求方式(0:GET,2:POST,3:PUT,5:DELETE)")
	private Integer requestMethod;

	@NotNull(message = "资源类型不能为空")
	@Range(min=0,max=1,message="资源校验类型(0按钮1菜单)")
//	@ApiModelProperty(value = "资源校验类型(0按钮1菜单)")
	private Integer type;

	@JsonFormat
//	@ApiModelProperty(value = "资源创建时间")
	private Date createTime;

	@JsonFormat
//	@ApiModelProperty(value = "资源更新时间")
	private Date updateTime;
	
	@JsonIgnore
	public HttpMethod getHttpMethodEnum() {
		return EnumUtil.getByCode(requestMethod,HttpMethod.class);
	}
	
	@JsonIgnore
	public LinkType getLinkTypeEnum() {
		return EnumUtil.getByCode(type,LinkType.class);
	}
}
