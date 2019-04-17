/**
 * 
 */
package com.guoyao.auth.authorize.repository;

import org.springframework.stereotype.Repository;

import com.guoyao.auth.authorize.model.User;

/**
 * @author wuchao
 * @Date 【2019年1月16日:下午2:48:55】
 */
@Repository
public interface UserRepository extends AuthRepository<User> {

	User findByUsername(String username);

	User findByPhone(String account);

	User findByEmail(String account);
}
