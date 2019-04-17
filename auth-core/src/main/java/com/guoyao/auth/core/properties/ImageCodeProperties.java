/**
 * 
 */
package com.guoyao.auth.core.properties;

import lombok.Data;
import lombok.ToString;

/**图片验证码配置项
 * @author wuchao
 * @Date 【2019年2月13日:下午3:19:36】
 */
@Data
@ToString
public class ImageCodeProperties extends SmsCodeProperties {

	/**
	 * 图片宽
	 */
	private int width = 67;
	/**
	 * 图片高
	 */
	private int height = 23;
	
	public ImageCodeProperties() {
		setLength(4);
	}
}
