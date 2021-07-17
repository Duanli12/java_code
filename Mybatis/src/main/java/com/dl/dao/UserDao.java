package com.dl.dao;

import com.dl.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @ClassName UserDao
 * @description
 * @author:duanli
 * @createDate:2020/9/15 14:17
 */
public interface UserDao {
    Set<User> queryAll();
    int insertByUser(User user);
    int deleteByUser(User user);
    int updateByUser(User user);
    int deleteById(User user);
    int deleteByIds(@Param("userIds") Set<Integer> userIds);
    Set<User> queryByColumn(@Param("column") String column,@Param("columnValue") String columnValue);
    Set<User> queryByMultiConditions(@Param("userName")String userName,@Param("userPhone")String userPhone,@Param("userRole")String userRole);

}
