/**
 * 
 */
package com.guoyao.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoyao.auth.model.BookChapter;
import com.guoyao.auth.repository.BookChapterRepository;
import com.guoyao.auth.service.BookChapterService;

/**
 * @author wuchao
 * @Date 【2019年3月7日:下午5:21:29】
 */
@Service
public class BookChapterServiceImpl implements BookChapterService {
	
	@Autowired
	private BookChapterRepository bookChapterRepository;

	/* (non-Javadoc)
	 * @see com.guoyao.auth.service.BookChapterService#findOne()
	 */
	@Override
	public BookChapter findOne(Long id) {
		BookChapter bookChapter = bookChapterRepository.findOne(id);
		BookChapter chapter = new BookChapter();
		if(bookChapter != null) {
			chapter.setId(bookChapter.getId());
			chapter.setTitle(bookChapter.getTitle());
			chapter.setContent(bookChapter.getContent());
		}
		return chapter;
	}
}
