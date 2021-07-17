package com.hqyj.dl.dao;

import com.hqyj.dl.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @ClassName UserDao
 * @description
 * @author:duanli
 * @createDate:2020/9/15 14:17
 */
public interface UserDao {
    List<User> findByAddressUser(@Param("column") String column, @Param("columnValue") String columnValue);
    List<User> queryAll();
    User queryByUsername(String username);
    User queryById(Integer userId);
    int insertByUser(User user);
    int deleteByUser(User user);
    int updateByUser(User user);
    int deleteById(User user);
    int deleteByIds(@Param("userIds") Set<Integer> userIds);
    Set<User> queryByColumn(@Param("column") String column, @Param("columnValue") String columnValue);
    Set<User> queryByMultiConditions(@Param("userName") String userName, @Param("userPhone") String userPhone, @Param("userRole") String userRole);

}
