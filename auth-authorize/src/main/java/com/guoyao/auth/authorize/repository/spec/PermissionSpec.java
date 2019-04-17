/**
 * 
 */
package com.guoyao.auth.authorize.repository.spec;

import com.guoyao.auth.authorize.model.Permission;
import com.guoyao.auth.authorize.repository.support.QueryWraper;
import com.guoyao.auth.authorize.repository.support.RepositorySpecification;
import com.guoyao.auth.authorize.web.controller.query.PermissionQueryCondition;

/**
 * @author wuchao
 * @Date 【2019年1月22日:上午10:54:27】
 */
public class PermissionSpec extends RepositorySpecification<Permission,PermissionQueryCondition> {

	public PermissionSpec(PermissionQueryCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Permission> queryWraper) {
		addLikeCondition(queryWraper, "name");
		addLikeCondition(queryWraper, "url");
	}
}
