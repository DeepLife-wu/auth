/**
 * 
 */
package com.guoyao.auth.authorize.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wuchao
 * @Date 【2019年1月16日:上午10:04:45】
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude= {"roles"})
@Entity
@Table(name = "permission")
public class Permission {
	
	/**
	 * 资源ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	/**
	 * 资源名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 资源链接
	 */
	@Column(name = "url")
	private String url;
	
	/**
	 * 资源请求方式:get ,post ,put , delete
	 */
//	@Enumerated(EnumType.STRING)
	@Column(name = "method")
	private Integer requestMethod;

	/**
	 * 0按钮1菜单
	 */
//	@Enumerated(EnumType.ORDINAL)
	@Column(name = "type")
	private Integer type;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
	
	@Builder.Default
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_permission", joinColumns = { @JoinColumn(name = "permission_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@NotFound(action = NotFoundAction.IGNORE)
	@OrderBy(value = "id ASC")
	private Set<Role> roles = new HashSet<Role>();
	
}
