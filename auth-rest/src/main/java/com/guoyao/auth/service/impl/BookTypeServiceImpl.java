/**
 * 
 */
package com.guoyao.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoyao.auth.model.BookType;
import com.guoyao.auth.repository.BookTypeRepository;
import com.guoyao.auth.service.BookTypeService;

/**
 * @author wuchao
 * @Date 【2019年3月7日:下午5:22:04】
 */
@Service
public class BookTypeServiceImpl implements BookTypeService {
	
	@Autowired
	private BookTypeRepository bookTypeRepository;

	/* (non-Javadoc)
	 * @see com.guoyao.auth.service.BookTypeService#findOne(java.lang.Long)
	 */
	@Override
	public BookType findOne(Long id) {
		BookType one = bookTypeRepository.findOne(id);
		BookType two = new BookType();
		if(one != null) {
			two.setId(one.getId());
			two.setName(one.getName());
			two.setNote(one.getNote());
		}
		return two;
	}

	@Override
	public List<BookType> findAll() {
		List<BookType> bookTypeList = bookTypeRepository.findAll();
		List<BookType> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(bookTypeList)) {
			for(BookType type : bookTypeList) {
				BookType bt = new BookType();
				bt.setId(type.getId());
				bt.setName(type.getName());
				bt.setNote(type.getNote());
				result.add(bt);
			}
		}
		return result;
	}

}
