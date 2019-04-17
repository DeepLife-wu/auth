package com.guoyao.auth.authorize.repository.spec;

import com.guoyao.auth.authorize.model.Menu;
import com.guoyao.auth.authorize.repository.support.QueryWraper;
import com.guoyao.auth.authorize.repository.support.RepositorySpecification;
import com.guoyao.auth.authorize.web.controller.query.MenuQueryCondition;

/**
 * 
 * @author wuchao
 * @Date 【2019年1月24日:上午10:32:26】
 */
public class MenuSpec extends RepositorySpecification<Menu, MenuQueryCondition> {

	public MenuSpec(MenuQueryCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Menu> queryWraper) {
		addLikeCondition(queryWraper, "name");
		addIsNullCondition(queryWraper, "parentId",true);
	}
}
