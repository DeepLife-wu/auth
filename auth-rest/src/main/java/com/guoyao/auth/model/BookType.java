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
 * [2019年3月7日 下午1:46:57]
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude= {"bookinfos"})
@Entity
@Table(name = "book_type", catalog = "test", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class BookType implements java.io.Serializable {
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "note", length = 65535)
	private String note;
	
	@OrderBy("id asc")
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bookType")
//	private Set<Bookinfo> bookinfos = new LinkedHashSet<Bookinfo>();
	private List<Bookinfo> bookinfos = new ArrayList<>();
	
}
