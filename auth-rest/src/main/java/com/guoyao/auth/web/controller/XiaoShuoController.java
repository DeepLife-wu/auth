/**
 * 
 */
package com.guoyao.auth.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guoyao.auth.authorize.exception.RestDataException;
import com.guoyao.auth.authorize.web.consts.ServerCode;
import com.guoyao.auth.authorize.web.vo.ResultVO;
import com.guoyao.auth.model.BookChapter;
import com.guoyao.auth.model.BookType;
import com.guoyao.auth.model.Bookinfo;
import com.guoyao.auth.service.BookChapterService;
import com.guoyao.auth.service.BookTypeService;
import com.guoyao.auth.service.BookinfoService;
import com.guoyao.auth.service.SolrService;
import com.guoyao.auth.web.form.SearchResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuchao
 * @Date 【2019年3月7日:下午3:33:20】
 */
@Slf4j
@RestController
@RequestMapping("/book")
public class XiaoShuoController {
	
	@Autowired
	private BookTypeService bookTypeService;
	@Autowired
	private BookinfoService bookinfoService;
	@Autowired
	private BookChapterService bookChapterService;
	
	@Autowired
	private SolrService solrService;

	@GetMapping("/type/{id:\\d+}")
	public ResultVO bookType(@PathVariable Long id) {
		BookType bookType = bookTypeService.findOne(id);
		if(bookType == null) throw new RestDataException(ServerCode.BOOK_TYPE_NOT_EXIST);
		return ResultVO.success(bookType);
	}
	
	@GetMapping("/type/list")
	public ResultVO bookTypeList() {
		List<BookType> bookTypeList = bookTypeService.findAll();
		if(bookTypeList.size() <= 0) throw new RestDataException(ServerCode.BOOK_TYPE_NOT_EXIST);
		return ResultVO.success(bookTypeList);
	}
	
	@GetMapping("/list/{id:\\d+}")
	public ResultVO bookListByType(@PathVariable Long id) {
		List<Bookinfo> bookList = bookinfoService.findBookList(id);
		return ResultVO.success(bookList);
	}
	
	@GetMapping("/list")
	public ResultVO bookList() {
		List<Bookinfo> bookList = bookinfoService.findAll();
		return ResultVO.success(bookList);
	}
	
	@GetMapping("/chapter/{id:\\d+}")
	public ResultVO bookChapter(@PathVariable Long id) {
		BookChapter bookChapter = bookChapterService.findOne(id);
		if(bookChapter == null) throw new RestDataException(ServerCode.BOOK_CHAPTER_NOT_EXIST);
		return ResultVO.success(bookChapter);
	}
	
	@GetMapping("/chapter/list/{id:\\d+}")
	public ResultVO bookChapterList(@PathVariable Long id) {
		Bookinfo bookinfo = bookinfoService.findOne(id);
		return ResultVO.success(bookinfo.getBookChapters());
	}
	
	//全文检索
	@GetMapping("/search")
	public ResultVO searchBook(
			@RequestParam(value = "keywords") String queryString,
			@RequestParam(value = "page", defaultValue = "1") Integer page, 
			@RequestParam(value = "rows", defaultValue="20")Integer rows) throws Exception {
		
		if(StringUtils.isBlank(queryString)) {
			return ResultVO.error(ServerCode.BOOK_SEARCH_CONDITION_NOT_EMPTY);
		}
		
		SearchResult searchResult = solrService.searchItem(queryString, page, rows);
		return ResultVO.success(searchResult);
	}
}
