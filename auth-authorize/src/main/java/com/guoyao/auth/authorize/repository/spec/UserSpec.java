package com.guoyao.auth.authorize.repository.spec;

import com.guoyao.auth.authorize.model.User;
import com.guoyao.auth.authorize.repository.support.QueryWraper;
import com.guoyao.auth.authorize.repository.support.RepositorySpecification;
import com.guoyao.auth.authorize.web.controller.query.UserQueryCondition;

/**
 * 
 * @author wuchao
 * @Date 【2019年1月24日:上午10:32:26】
 */
public class UserSpec extends RepositorySpecification<User, UserQueryCondition> {

	public UserSpec(UserQueryCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<User> queryWraper) {
		addLikeCondition(queryWraper, "username");
		addEqualsCondition(queryWraper, "age");
		addEqualsCondition(queryWraper, "gender");
		addLikeCondition(queryWraper, "email");
		addLikeCondition(queryWraper, "phone");
		addEqualsCondition(queryWraper, "status");
	}

}
