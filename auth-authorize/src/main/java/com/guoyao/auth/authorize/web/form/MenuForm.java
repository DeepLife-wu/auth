/**
 * 
 */
package com.guoyao.auth.authorize.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuchao
 * @Date 【2019年1月25日:下午3:30:57】
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MenuForm {

	@Null(message="id必须为空")
//	@ApiModelProperty(value = "菜单id")
	private Long id;
	
	@Min(value=1,message="父级id不能比1小")
//	@ApiModelProperty(value = "菜单父级id")
	private Long parentId;
	
	@NotBlank(message = "菜单名称不能为空")
//	@ApiModelProperty(value = "菜单名称")
	private String name;
	
//	@ApiModelProperty(value = "菜单URL")
	private String menuUrl = "javascript:;";
	
	@NotNull(message = "sort不能为空")
	@Min(value=1,message="sort不能比1小")
//	@ApiModelProperty(value = "菜单排序")
	private Integer sort;
	
//	@ApiModelProperty(value = "菜单创建时间")
	@JsonFormat
	private Date createTime;//创建时间
	
	@Builder.Default
//	@ApiModelProperty(value = "子节点集合")
	private List<MenuForm> childrenList = new ArrayList<>();
}
