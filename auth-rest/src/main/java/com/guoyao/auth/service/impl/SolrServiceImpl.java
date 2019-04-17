/**
 * 
 */
package com.guoyao.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoyao.auth.model.Bookinfo;
import com.guoyao.auth.repository.dao.SearchBookDao;
import com.guoyao.auth.service.BookinfoService;
import com.guoyao.auth.service.SolrService;
import com.guoyao.auth.web.form.SearchResult;
import com.guoyao.auth.web.form.SolrBookinfoForm;

/**
 * @author wuchao
 * @Date 【2019年3月14日:下午5:59:44】
 */
@Service
public class SolrServiceImpl implements SolrService {
	
	@Autowired
	private BookinfoService bookinfoService;
	
	@Autowired
    private SolrClient solrClient;
	
	@Autowired
	private SearchBookDao searchBookDao;
	
	/* (non-Javadoc)
	 * @see com.guoyao.auth.service.SolrService#syncBookinfo2IndexStock()
	 */
	@Override
	public void syncBookinfo2IndexStock() throws Exception {
//		List<Bookinfo> bookinfoList = bookinfoService.findAll();
		List<SolrBookinfoForm> bookinfoList = bookinfoService.associateSelectBook();
		if(CollectionUtils.isNotEmpty(bookinfoList)) {
			/*List<SolrBookinfoForm> solrList = new ArrayList<>();
			for(SolrBookinfoForm info : bookinfoList) {
				SolrBookinfoForm form = new SolrBookinfoForm();
				form.setId(info.getId());
				form.setName(info.getName());
				form.setNote(info.getNote());
				form.setAuthor(info.getAuthor());
				form.setImage(info.getImage());
				form.setTitle(info.getTitle());
				solrList.add(form);
			}*/
			solrClient.addBeans(bookinfoList);
			solrClient.commit();
		}
	}

	@Override
	public void deleteAllIndex() throws Exception {
		List<Bookinfo> bookinfoList = bookinfoService.findAll();
		if(CollectionUtils.isNotEmpty(bookinfoList)) {
			List<String> ids = new ArrayList<>();
			for(Bookinfo info : bookinfoList) {
				ids.add(info.getId() + "");
			}
			solrClient.deleteById(ids);
			solrClient.commit();
		}
	}

	@Override
	public SearchResult searchItem(String queryString, Integer page ,Integer rows) throws Exception {
		//创建一个查询对象
		SolrQuery query = new SolrQuery();
		//查询条件
		query.setQuery(queryString);
		//设置分页
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		//设置默认搜素域
		query.set("df", "book_keywords");
		//设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("book_name");
		query.addHighlightField("book_title");
		query.addHighlightField("book_author");
		query.addHighlightField("book_note");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		//执行查询
		SearchResult searchResult = searchBookDao.searchBook(query);
		//计算查询结果总页数
		Long recordCount = searchResult.getRecordCount();
		Long pageCount = recordCount / rows;
		if(recordCount % rows > 0) {
			pageCount++;
		}
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page.longValue());
		return searchResult;
	}
}
