/**
 * 
 */
package com.guoyao.auth.authorize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.guoyao.auth.authorize.model.UserConnection;
import com.guoyao.auth.authorize.model.embedd.GuoyaoUserConnectionId;

/**
 * @author wuchao
 * @Date 【2019年1月16日:下午2:48:55】
 */
@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection, GuoyaoUserConnectionId>,JpaSpecificationExecutor<UserConnection> {

	@Query(value = "from UserConnection WHERE userId = ?1")
	UserConnection findByUserId(String username);

}
