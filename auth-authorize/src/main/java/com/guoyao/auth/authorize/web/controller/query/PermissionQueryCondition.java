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
public class PermissionQueryCondition {
//	@ApiModelProperty(value = "资源中文名称")
	private String name;
	
//	@ApiModelProperty(value = "资源链接地址")
	private String url;
}
