/**
 * 
 */
package com.guoyao.auth.service;

import java.util.List;

import com.guoyao.auth.model.BookType;

/**
 * @author wuchao
 * @Date 【2019年3月7日:下午5:16:43】
 */
public interface BookTypeService {
	
	BookType findOne(Long id);

	List<BookType> findAll();
	
	
}
