package com.hqyj.dao;

import com.hqyj.pojo.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleInfoDao extends JpaRepository<RoleInfo,Integer> {

    //jpa 自定义sql
    //Transactional  自定义增删改操作需要加的注解
    @Transactional
    //@Modifying 表示执行增、删、改操作，clearAutomatically = true 表示清理实体类里的数据。
    @Modifying(clearAutomatically = true)
    //nativeQuery = true 支持原生sql ,false 表达支持hql语句
    @Query(value="delete from user_role_relation where u_id=:uId",nativeQuery = true)
    void deleteUserRolet(@Param("uId") Integer uId);

    //jpa 自定义sql
    //Transactional  自定义增删改操作需要加的注解
    @Transactional
    //@Modifying 表示执行增、删、改操作，clearAutomatically = true 表示清理实体类里的数据。
    @Modifying(clearAutomatically = true)
    //nativeQuery = true 支持原生sql ,false 表达支持hql语句
    @Query(value="delete from user_role_relation where u_id in(:uId)",nativeQuery = true)
    void deleteUserRoleAll(@Param("uId")List<String> uId);

}
