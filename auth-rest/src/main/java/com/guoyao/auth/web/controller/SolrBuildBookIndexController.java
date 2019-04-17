/**
 * 
 */
package com.guoyao.auth.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guoyao.auth.authorize.web.vo.ResultVO;
import com.guoyao.auth.service.SolrService;

/**
 * @author wuchao
 * @Date 【2019年3月14日:下午6:12:27】
 */
@RestController
@RequestMapping("/book/idx")
public class SolrBuildBookIndexController {
	
	@Autowired
	private SolrService solrService;
	
	@GetMapping("/build/all")
	public ResultVO buildIndex() throws Exception {
		solrService.syncBookinfo2IndexStock();
		return ResultVO.success();
	}
	
	@GetMapping("/delete/all")
	public ResultVO deleteAllIndex() throws Exception {
		solrService.deleteAllIndex();
		return ResultVO.success();
	}

}
