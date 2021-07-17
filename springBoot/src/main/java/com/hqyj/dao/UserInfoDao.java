package com.hqyj.dao;

import com.hqyj.pojo.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository //可选注解
public interface UserInfoDao extends PagingAndSortingRepository<UserInfo,Integer> {
    /**
     * 登录
     * @param userName
     * @param userPwd
     * @return
     */
    UserInfo findByUserNameAndUserPwd(@Param("userName") String userName,@Param("userPwd") String userPwd);
    //查询用户名输入是否正确
    UserInfo findByUserName(@Param("userName") String userName);

    //jpa 自定义sql
    //Transactional  自定义增删改操作需要加的注解
    @Transactional
    //@Modifying 表示执行增、删、改操作，clearAutomatically = true 表示清理实体类里的数据。
    @Modifying(clearAutomatically = true)
    //nativeQuery = true 支持原生sql ,false 表达支持hql语句
    @Query(value="delete from user_info where u_id in(:uId)",nativeQuery = true)
    void deleteUserAll(@Param("uId") List<String> uId);

    //jpa 自定义sql
    //Transactional  自定义增删改操作需要加的注解
    @Transactional
    //@Modifying 表示执行增、删、改操作，clearAutomatically = true 表示清理实体类里的数据。
    @Modifying(clearAutomatically = true)
    //nativeQuery = true 支持原生sql ,false 表达支持hql语句
    @Query(value="delete from user_detail where ud_id in(:uId)",nativeQuery = true)
    void deleteUserTailAll(@Param("uId") List<String> uId);


    //根据用户查询
    Page<UserInfo> findByUserName(String userName, Pageable pageable);
    //根据邮箱
    Page<UserInfo>  findByUserDetailEmailLike(String email, Pageable pageable);

    //根据 用户名和邮箱
    Page<UserInfo>  findByUserNameAndUserDetailEmail(String userName,String email, Pageable pageable);
}
