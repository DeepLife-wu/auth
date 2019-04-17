/**
 * 
 */
package com.guoyao.auth.authorize.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.guoyao.auth.authorize.model.embedd.GuoyaoUserConnectionId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wuchao
 * @Date 【2019年2月28日:下午5:36:00】
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "`UserConnection`", catalog = "test", uniqueConstraints = @UniqueConstraint(columnNames = 
{ "userId", "providerId", "rank" }))
public class UserConnection implements java.io.Serializable {
	// Property accessors
	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "userId", column = @Column(name = "userId", nullable = false)),
			@AttributeOverride(name = "providerId", column = @Column(name = "providerId", nullable = false)),
			@AttributeOverride(name = "providerUserId", column = @Column(name = "providerUserId", nullable = false)) })
	private GuoyaoUserConnectionId guoyaoUserConnectionId;
	
	@Column(name = "rank", nullable = false)
	private Integer rank;
	
	@Column(name = "displayName")
	private String displayName;
	
	@Column(name = "profileUrl", length = 512)
	private String profileUrl;
	
	@Column(name = "imageUrl", length = 512)
	private String imageUrl;
	
	@Column(name = "accessToken", nullable = false, length = 512)
	private String accessToken;
	
	@Column(name = "secret", length = 512)
	private String secret;
	
	@Column(name = "refreshToken", length = 512)
	private String refreshToken;
	
	@Column(name = "expireTime")
	private Long expireTime;
	
	
}
