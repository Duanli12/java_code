package com.hqyj.dl.service;


import com.github.pagehelper.PageInfo;
import com.hqyj.dl.pojo.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName UserService
 * @description
 * @author:duanli
 * @createDate:2020/9/15 14:18
 */
public interface UserService {
    List<User> findByAddressUser(String column,String value);
    List<User> getAll();
    User getOneByUserId(Integer userId);
    User getOneByUsername(String username);
    PageInfo<User> getUserByPage(Integer currentPage, Integer pageSize);
    boolean addOne(User user);
    boolean removeOne(User user);
    boolean modifyOne(User user);
    boolean removeSome(Set<Integer> userIds);
    Set<User> getUserThatAddressLike(String address);
    Set<User> getUserWithMultiCondition(String userName, String userPhone, String roleName);

    Map<String,Object> login(String username,String password);
    User logout();

}
