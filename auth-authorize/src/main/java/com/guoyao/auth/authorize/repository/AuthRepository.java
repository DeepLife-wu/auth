/**
 * 
 */
package com.guoyao.auth.authorize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author wuchao
 * @Date 【2019年1月22日:上午10:36:44】
 */
@NoRepositoryBean
public interface AuthRepository<T> extends JpaRepository<T, Long>,JpaSpecificationExecutor<T> {

}
