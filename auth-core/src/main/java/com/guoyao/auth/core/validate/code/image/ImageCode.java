/**
 * 
 */
package com.guoyao.auth.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.guoyao.auth.core.validate.code.ValidateCode;

import lombok.Data;
import lombok.ToString;

/**
 * @author wuchao
 * @Date 【2019年2月13日:下午3:59:05】
 */
@Data
@ToString
public class ImageCode extends ValidateCode {

	private BufferedImage image; 
	
	public ImageCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
}
