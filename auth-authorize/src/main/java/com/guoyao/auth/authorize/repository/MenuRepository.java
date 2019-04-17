/**
 * 
 */
package com.guoyao.auth.authorize.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.guoyao.auth.authorize.model.Menu;

/**
 * @author wuchao
 * @Date 【2019年1月16日:下午2:48:55】
 */
@Repository
public interface MenuRepository extends AuthRepository<Menu> {
	@Query(value = "from Menu where parentId is null order by sort asc")
	List<Menu> findRootMenu();

}
