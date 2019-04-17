/**
 * 
 */
package com.guoyao.auth.repository.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.guoyao.auth.web.form.SearchResult;

/**
 * @author wuchao
 * @Date 【2019年3月14日:下午6:37:42】
 */
public interface SearchBookDao {

	SearchResult searchBook(SolrQuery solrQuery) throws Exception;
}
