/**
 * 
 */
package com.guoyao.auth.service;

import java.util.List;

import com.guoyao.auth.model.Bookinfo;
import com.guoyao.auth.web.form.SolrBookinfoForm;

/**
 * @author wuchao
 * @Date 【2019年3月7日:下午5:19:28】
 */
public interface BookinfoService {
	
	List<SolrBookinfoForm> associateSelectBook();

	List<Bookinfo> findAll();
	
	Bookinfo findOne(Long id);

	List<Bookinfo> findBookList(Long id);
}
