/**
 * 
 */
package com.guoyao.auth.authorize.repository.support;

import org.springframework.core.convert.converter.Converter;

/**
 * @author wuchao
 * @Date 【2019年1月7日:下午5:25:46】
 */
public interface Domain2InfoConverter<T, I> extends Converter<T,I> {
	
}
