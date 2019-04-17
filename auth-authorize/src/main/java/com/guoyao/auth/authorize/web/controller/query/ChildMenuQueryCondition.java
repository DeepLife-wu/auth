/**
 * 
 */
package com.guoyao.auth.authorize.web.controller.query;

import lombok.Data;
import lombok.ToString;

/**子菜单查询条件
 * @author wuchao
 * @Date 【2019年1月25日:下午3:31:11】
 */
@Data
@ToString
public class ChildMenuQueryCondition {

//	@ApiModelProperty(value = "菜单名称")
	private String name;
	
//	@ApiModelProperty(value = "菜单父级id")
	private Long parentId;
}
