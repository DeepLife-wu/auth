/**
 * 
 */
package com.guoyao.auth.authorize.repository.support;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.Data;

/**
 * 包装用于构建JPA动态查询时所需的对象
 * @author wuchao
 * @Date 【2019年1月8日:上午10:09:57】
 * @param <T>
 */
@Data
public class QueryWraper<T> {
	/** JPA Root*/
	private Root<T> root;
	/** JPA CriteriaBuilder*/
	private CriteriaBuilder cb;
	/** JPA Predicate 集合*/
	private List<Predicate> predicates;
	/** JPA 查询对象*/
	private CriteriaQuery<?> query;
	
	/**
	 * @param root JPA Root
	 * @param query JPA query
	 * @param cb JPA CriteriaBuilder
	 * @param predicates JPA Predicate 集合
	 */
	public QueryWraper(Root<T> root,CriteriaQuery<?> query,CriteriaBuilder cb,List<Predicate> predicates) {
		this.root = root;
		this.query = query;
		this.cb = cb;
		this.predicates = predicates;
	}
	
	public void addPredicate(Predicate predicate) {
		this.predicates.add(predicate);
	}
}
