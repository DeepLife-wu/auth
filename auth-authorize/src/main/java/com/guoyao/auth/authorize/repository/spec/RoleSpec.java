/**
 * 
 */
package com.guoyao.auth.authorize.repository.spec;

import com.guoyao.auth.authorize.model.Role;
import com.guoyao.auth.authorize.repository.support.QueryWraper;
import com.guoyao.auth.authorize.repository.support.RepositorySpecification;
import com.guoyao.auth.authorize.web.controller.query.RoleQueryCondition;

/**
 * @author wuchao
 * @Date 【2019年1月23日:下午5:11:06】
 */
public class RoleSpec extends RepositorySpecification<Role, RoleQueryCondition> {
	public RoleSpec(RoleQueryCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Role> queryWraper) {
		addLikeCondition(queryWraper, "code");
		addLikeCondition(queryWraper, "name");
	}
}
