/**
 * 
 */
package com.guoyao.auth.authorize.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guoyao.auth.authorize.model.Permission;

/**
 * @author wuchao
 * @Date 【2019年1月16日:下午2:48:55】
 */
@Repository
public interface PermissionRepository extends AuthRepository<Permission>{

	List<Permission> findByType(Integer menuType);

}
