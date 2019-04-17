/**
 * 
 */
package com.guoyao.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoyao.auth.model.BookChapter;
import com.guoyao.auth.model.BookType;
import com.guoyao.auth.model.Bookinfo;
import com.guoyao.auth.repository.BookinfoRepository;
import com.guoyao.auth.service.BookinfoService;
import com.guoyao.auth.web.form.SolrBookinfoForm;

/**
 * @author wuchao
 * @Date 【2019年3月7日:下午5:21:46】
 */
@Service
public class BookinfoServiceImpl implements BookinfoService {
	
	@Autowired
	private BookinfoRepository bookinfoRepository;

	/* (non-Javadoc)
	 * @see com.guoyao.auth.service.BookinfoService#findAll()
	 */
	@Override
	public List<Bookinfo> findAll() {
		List<Bookinfo> bookinfoList = bookinfoRepository.findAll();
		List<Bookinfo> bookList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(bookinfoList)) {
			for(Bookinfo info : bookinfoList) {
				Bookinfo book = new Bookinfo();
				book.setId(info.getId());
				book.setName(info.getName());
				book.setNote(info.getNote());
				book.setImage(info.getImage());
				book.setAuthor(info.getAuthor());
				
				BookType type = info.getBookType();
				if(type != null) {
					BookType bookType = new BookType();
					bookType.setId(type.getId());
					bookType.setName(type.getName());
					bookType.setNote(type.getNote());
					
					book.setBookType(bookType);
				}
				bookList.add(book);
			}
		}
		return bookList;
	}

	/* (non-Javadoc)
	 * @see com.guoyao.auth.service.BookinfoService#findOne()
	 */
	@Override
	public Bookinfo findOne(Long id) {
		Bookinfo info = bookinfoRepository.findOne(id);
		
		Bookinfo book = new Bookinfo();
		if(info != null) {
			book.setId(info.getId());
			book.setName(info.getName());
			book.setNote(info.getNote());
			
			BookType type = info.getBookType();
			if(type != null) {
				BookType bookType = new BookType();
				bookType.setId(type.getId());
				bookType.setName(type.getName());
				bookType.setNote(type.getNote());
				
				book.setBookType(bookType);
			}
			
			List<BookChapter> clist = info.getBookChapters();
			if(CollectionUtils.isNotEmpty(clist)) {
				for(BookChapter chapt : clist) {
					BookChapter bc = new BookChapter();
					bc.setId(chapt.getId());
//					bc.setContent(chapt.getContent());
					bc.setTitle(chapt.getTitle());
					book.getBookChapters().add(bc);
				}
			}
		}
		return book;
	}

	@Override
	public List<Bookinfo> findBookList(Long id) {
		List<Bookinfo> bookList = bookinfoRepository.findBookList(id);
		List<Bookinfo> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(bookList)) {
			for(Bookinfo info : bookList) {
				Bookinfo book = new Bookinfo();
				book.setId(info.getId());
				book.setName(info.getName());
				book.setNote(info.getNote());
				book.setImage(info.getImage());
				book.setAuthor(info.getAuthor());
				result.add(book);
			}
		}
		return result;
	}

	@Override
	public List<SolrBookinfoForm> associateSelectBook() {
		List<Object[]> result = bookinfoRepository.associateSelectBook();
		List<SolrBookinfoForm> bookList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(result)) {
			for(Object[] record : result) {
				SolrBookinfoForm book = new SolrBookinfoForm();
				book.setId(record[0].toString());
				book.setTitle((String)record[1]);
				book.setName((String)record[2]);
				book.setNote((String)record[3]);
				book.setAuthor((String)record[4]);
				book.setImage((String)record[5]);
				bookList.add(book);
			}
		}
		return bookList;
	}
}
