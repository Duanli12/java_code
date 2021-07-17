package com.dl.service;

import com.dl.pojo.User;

import java.util.List;
import java.util.Set;

/**
 * @ClassName UserService
 * @description
 * @author:duanli
 * @createDate:2020/9/15 14:18
 */
public interface UserService {
    Set<User> getAll();
    boolean addOne(User user);
    boolean removeOne(User user);
    boolean modifyOne(User user);
    boolean removeSome(Set<Integer> userIds);
    Set<User> getUserThatAddressLike(String address);
    Set<User> getUserWithMultiCondition(String userName,String userPhone,String roleName);

}
