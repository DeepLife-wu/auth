/**
 * 
 */
package com.guoyao.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guoyao.auth.model.BookChapter;

/**
 * @author wuchao
 * [2019年3月7日 下午2:10:30]
 */
@Repository
public interface BookChapterRepository extends JpaRepository<BookChapter, Long>{

}
