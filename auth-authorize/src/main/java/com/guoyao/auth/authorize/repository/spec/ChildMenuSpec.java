/**
 * 
 */
package com.guoyao.auth.authorize.repository.spec;

import com.guoyao.auth.authorize.model.Menu;
import com.guoyao.auth.authorize.repository.support.QueryWraper;
import com.guoyao.auth.authorize.repository.support.RepositorySpecification;
import com.guoyao.auth.authorize.web.controller.query.ChildMenuQueryCondition;

/**
 * @author wuchao
 * @Date 【2019年2月1日:下午2:46:05】
 */
public class ChildMenuSpec extends RepositorySpecification<Menu, ChildMenuQueryCondition> {

	public ChildMenuSpec(ChildMenuQueryCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Menu> queryWraper) {
		addLikeCondition(queryWraper, "name");
		addEqualsCondition(queryWraper, "parentId");
	}
}
