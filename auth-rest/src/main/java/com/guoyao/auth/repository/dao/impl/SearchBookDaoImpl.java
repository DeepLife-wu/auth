/**
 * 
 */
package com.guoyao.auth.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoyao.auth.repository.dao.SearchBookDao;
import com.guoyao.auth.web.form.SearchResult;
import com.guoyao.auth.web.form.SolrBookinfoForm;

/**
 * @author wuchao
 * @Date 【2019年3月14日:下午6:38:54】
 */
@Service
public class SearchBookDaoImpl implements SearchBookDao {

	@Autowired
	private SolrClient solrClient;

	@Override
	public SearchResult searchBook(SolrQuery solrQuery) throws Exception {
		//根据查询条件搜索索引库
		QueryResponse response = solrClient.query(solrQuery);
		//取书籍列表
		SolrDocumentList documentList = response.getResults();
		List<SolrBookinfoForm> bookList = new ArrayList<>();
		
		if(CollectionUtils.isNotEmpty(documentList)) {
			for(SolrDocument solrDocument : documentList) {
				SolrBookinfoForm item = new SolrBookinfoForm();
				item.setId(solrDocument.get("id").toString());
				
				//取高亮显示
				Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
				List<String> list = highlighting.get(solrDocument.get("id")).get("book_title");
				String title = "";
				if(CollectionUtils.isNotEmpty(list)) {
					title = list.get(0);
				} else {
					title = solrDocument.get("book_title").toString();
				}
				
				item.setTitle(title);
				
				list = highlighting.get(solrDocument.get("id")).get("book_name");
				String name = "";
				if(CollectionUtils.isNotEmpty(list)) {
					name = list.get(0);
				} else {
					name = solrDocument.get("book_name").toString();
				}
				item.setName(name);
				
				item.setAuthor(solrDocument.get("book_author").toString());
				item.setNote(solrDocument.get("book_note").toString());
				item.setImage(solrDocument.get("book_image").toString());
				
				bookList.add(item);
			}
		}
		
		SearchResult result = new SearchResult();
		//书籍列表
		result.setBookList(bookList);
		//总记录数据
		result.setRecordCount(documentList.getNumFound());
		return result;
	}
}
