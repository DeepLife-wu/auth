/**
 * 
 */
package com.guoyao.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.guoyao.auth.model.Bookinfo;
import com.guoyao.auth.web.form.SolrBookinfoForm;

/**
 * @author wuchao
 * [2019年3月7日 下午2:10:30]
 */
@Repository
public interface BookinfoRepository extends JpaRepository<Bookinfo, Long>{

//	@Query(value = "from Menu where parentId is null order by sort asc")
	@Query(nativeQuery = true ,value = "select * from bookinfo where type_id=?1")
	List<Bookinfo> findBookList(Long typeId);

	@Query(nativeQuery = true,value = "select c.id as id,c.title as title, b.name as name,b.note as note, b.author as author,b.image as image from book_chapter c left join bookinfo b on c.book_id=b.id")
	List<Object[]> associateSelectBook();
}
