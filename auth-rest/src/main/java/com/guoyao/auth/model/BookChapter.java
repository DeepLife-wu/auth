/**
 * 
 */
package com.guoyao.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wuchao
 * [2019年3月7日 下午1:47:33]
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude= {"bookinfo"})
@Entity
@Table(name = "book_chapter", catalog = "test")
public class BookChapter implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Bookinfo bookinfo;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "title", nullable = false, length = 100)
	private String title;
}
