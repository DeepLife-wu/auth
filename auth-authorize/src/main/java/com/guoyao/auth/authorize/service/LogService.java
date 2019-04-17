package com.guoyao.auth.authorize.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.guoyao.auth.authorize.model.GuoyaoLog;
import com.guoyao.auth.authorize.web.controller.query.LogQueryCondition;
import com.guoyao.auth.authorize.web.form.LogForm;

/**
 * 
 * @author wuchao
 * @Date 【2019年2月18日:下午4:54:13】
 */
public interface LogService {

	GuoyaoLog save(GuoyaoLog user);
	
	Page<LogForm> findByCondition(LogQueryCondition condition, Pageable pageable);
}
