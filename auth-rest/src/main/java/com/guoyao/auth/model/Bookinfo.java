/**
 * 
 */
package com.guoyao.auth.model;

import java.util.ArrayList;
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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wuchao
 * [2019年3月7日 下午1:45:23]
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude= {"bookChapters","bookType"})
@Entity
@Table(name = "bookinfo", catalog = "test", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Bookinfo implements java.io.Serializable {
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	private BookType bookType;
	
	@Column(name = "name", unique = true, nullable = false, length = 100)
	private String name;
	
	@Column(name = "note", length = 65535)
	private String note;
	
	@Column(name = "author", nullable = false, length = 100)
	private String author;
	
	@Column(name = "image", nullable = false, length = 100)
	private String image;
	
	@OrderBy("id asc")
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bookinfo")
//	private Set<BookChapter> bookChapters = new LinkedHashSet<BookChapter>();
	private List<BookChapter> bookChapters = new ArrayList<>();
	
}
