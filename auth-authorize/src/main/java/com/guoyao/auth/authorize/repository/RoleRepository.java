/**
 * 
 */
package com.guoyao.auth.authorize.repository;

import org.springframework.stereotype.Repository;

import com.guoyao.auth.authorize.model.Role;

/**
 * @author wuchao
 * @Date 【2019年1月16日:下午2:48:55】
 */
@Repository
public interface RoleRepository extends AuthRepository<Role> {

	Role findByCode(String code);

}
