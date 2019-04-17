/**
 * 
 */
package com.guoyao.auth.authorize.repository.support;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

/**
 * @author wuchao
 * @Date 【2019年2月22日:下午4:09:57】
 */
public class GuoyaoImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8302431232238469945L;

	@Override
	protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
//		return super.toIdentifier("guoyao_"+stringForm.toLowerCase(), buildingContext);
//		return super.toIdentifier("guoyao_" + stringForm, buildingContext);
		return super.toIdentifier(stringForm, buildingContext);
	}
}
