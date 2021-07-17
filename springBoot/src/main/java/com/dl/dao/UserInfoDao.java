package com.dl.dao;

import com.dl.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * @ClassName UserInfoDao
 * @description
 * @author:duanli
 * @createDate:2020.10.13 13:49
 */
@Repository //可选注解
public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
    //登录
    UserInfo findByUserNameAndUserPwd(@Param("userName") String userName,@Param("userPwd") String userPwd);
    UserInfo findByUserName(@Param("userName")String userName);

}
