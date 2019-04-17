/**
 * 
 */
package com.guoyao.auth.service;

import com.guoyao.auth.web.form.SearchResult;

/**
 * @author wuchao
 * @Date 【2019年3月14日:下午5:49:29】
 */
public interface SolrService {
	void syncBookinfo2IndexStock() throws Exception;
	
	void deleteAllIndex() throws Exception;
	
	SearchResult searchItem(String queryString, Integer page, Integer rows) throws Exception;
	
}
