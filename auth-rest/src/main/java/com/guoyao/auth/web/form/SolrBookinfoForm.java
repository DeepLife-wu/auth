/**
 * 
 */
package com.guoyao.auth.web.form;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wuchao
 * @Date 【2019年3月14日:下午5:51:01】
 */
@Getter
@Setter
public class SolrBookinfoForm implements Serializable {
	// Fields
	@Field("id")
	private String id;
	
	@Field("book_name")
	private String name;
	
	@Field("book_note")
	private String note;
	
	@Field("book_author")
	private String author;
	
	@Field("book_image")
	private String image;
	
	@Field("book_title")
	private String title;
}
