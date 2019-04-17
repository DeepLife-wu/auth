package com.guoyao.auth.authorize.repository.spec;

import com.guoyao.auth.authorize.model.GuoyaoLog;
import com.guoyao.auth.authorize.repository.support.QueryWraper;
import com.guoyao.auth.authorize.repository.support.RepositorySpecification;
import com.guoyao.auth.authorize.web.controller.query.LogQueryCondition;

/**
 * 
 * @author wuchao
 * @Date 【2019年1月24日:上午10:32:26】
 */
public class LogSpec extends RepositorySpecification<GuoyaoLog, LogQueryCondition> {

	public LogSpec(LogQueryCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<GuoyaoLog> queryWraper) {
		addLikeCondition(queryWraper, "username");
		addLikeCondition(queryWraper, "operation");
		addGreaterThanOrEqualCondition(queryWraper, "startTime","createTime");
		addLessThanOrEqualCondition(queryWraper, "endTime","createTime");
	}
}
