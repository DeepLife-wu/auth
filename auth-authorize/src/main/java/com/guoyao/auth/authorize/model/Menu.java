/**
 * 
 */
package com.guoyao.auth.authorize.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wuchao
 * @Date 【2019年1月16日:上午11:49:53】
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude= {"parent","childrenList"})
@Entity
@Table(name = "menu")
public class Menu {
	/** 菜单ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Long id;
	
	/** 菜单父级ID*/
	@Column(name = "parent_id")
	private Long parentId;
	
	/** 菜单名称*/
	@Column(name = "name")
	private String name;
	
	/** 菜单URL*/
	@Column(name = "menu_url")
	private String menuUrl;
	
	/** 菜单排序*/
	@Column(name = "sort")
	private Integer sort;
	
	/** 菜单创建时间*/
	@Column(name = "create_time")
	private Date createTime;//创建时间
	
	/** 子节点集合*/
	@OneToMany(mappedBy="parent", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@OrderBy("sort asc")
	private List<Menu> childrenList;
	
	/** 父节点*/
	@ManyToOne
	@JoinColumn(name="parent_id", insertable=false, updatable=false)
	private Menu parent;
	
}
