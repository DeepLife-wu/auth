package com.guoyao.auth.web.form;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchResult implements Serializable {
	private List<SolrBookinfoForm> bookList;
	private Long curPage;
	private Long pageCount;
	private Long recordCount;
}
