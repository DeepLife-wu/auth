/**
 * 
 */
package com.guoyao.auth.authorize.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guoyao.auth.authorize.model.GuoyaoLog;
import com.guoyao.auth.authorize.repository.LogRepository;
import com.guoyao.auth.authorize.repository.spec.LogSpec;
import com.guoyao.auth.authorize.repository.support.QueryResultConverter;
import com.guoyao.auth.authorize.service.LogService;
import com.guoyao.auth.authorize.web.controller.query.LogQueryCondition;
import com.guoyao.auth.authorize.web.form.LogForm;
import com.guoyao.auth.authorize.web.util.DateUtil;

/**
 * 
 * @author wuchao
 * @Date 【2019年2月18日:下午4:54:28】
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogRepository logRepository;

	@Override
	public GuoyaoLog save(GuoyaoLog log) {
		return logRepository.save(log);
	}

	@Override
	public Page<LogForm> findByCondition(LogQueryCondition condition, Pageable pageable) {
		if(condition.getEndTime() == null) {
			Calendar calendar = Calendar.getInstance();
			Date end = DateUtil.getEndDate(calendar.getTime());
			condition.setEndTime(end);
//			condition.setEndTime(DateUtil.ymdHms.format(end));
		}
		if(condition.getStartTime() == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR, -(7 * 24));
			Date start = DateUtil.getStartDate(calendar.getTime());
			condition.setStartTime(start);
//			condition.setStartTime(DateUtil.ymdHms.format(start));
		}
		Page<GuoyaoLog> logs = logRepository.findAll(new LogSpec(condition),pageable);
		return QueryResultConverter.convert(logs, LogForm.class,pageable);
	}
}
