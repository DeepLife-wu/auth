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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
 * @Date 【2019年1月16日:上午11:38:44】
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude= {"roles"})
@Entity
@Table(name = "user")
public class User {
	
	/**
	 * 用户id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * 用户中文名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 用户账号
	 */
	@Column(name = "username",unique = true)
	private String username;

	/**
	 * 用户密码
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 用户邮箱
	 */
	@Column(name = "email",unique = true)
	private String email;
	
	/**
	 * 用户手机号
	 */
	@Column(name = "phone",unique = true)
	private String phone;
	
	/**
	 * 年龄
	 */
	@Column(name = "age")
	private Integer age;
	
	/**
	 * 性别
	 */
//	@Enumerated(EnumType.ORDINAL)
	@Column(name = "gender")
	private Integer gender;
	
	/**
	 * 出生日期
	 */
	@Temporal(TemporalType.DATE) 
	@Column(name = "birthday")
	private Date birthday;
	
	/**
	 * 用户状态(0锁定,1启用)
	 */
//	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Integer status;
	
	/**
	 * 创建用户账号
	 */
	@Column(name = "operator")
	private String operator;
	
	/**
	 * 创建用户的IP
	 */
	@Column(name = "operate_ip")
	private String operateIp;
	
	/**
	 * 用户创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 用户更新时间
	 */
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "update_time")
	private Date updateTime;
	
	/**
	 * 用户最后一次登录的时间
	 */
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "last_login_time")
	private Date lastLoginTime;
	
	/**
	 * 用户登录系统的次数
	 */
	@Column(name = "login_count")
	private Long loginCount;
	
	@Builder.Default
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@NotFound(action = NotFoundAction.IGNORE)
	@OrderBy(value = "id ASC")
	private Set<Role> roles = new HashSet<Role>();
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumns(
		{
            @JoinColumn(name="userId",referencedColumnName="userId"),
            @JoinColumn(name="providerId",referencedColumnName="providerId"),
            @JoinColumn(name="providerUserId",referencedColumnName="providerUserId")
        }		
	)
	@NotFound(action = NotFoundAction.IGNORE)
	private UserConnection userConnection;
}
