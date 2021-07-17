package com.dl.service.impl;

import com.dl.dao.UserDao;
import com.dl.dao.UserRoleDao;
import com.dl.pojo.Role;
import com.dl.pojo.User;
import com.dl.pojo.UserRole;
import com.dl.service.UserService;

import java.util.Set;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/15 14:06
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserRoleDao userRoleDao;

    public UserServiceImpl(UserDao userDao, UserRoleDao userRoleDao) {

        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }

    @Override
    public Set<User> getAll() {
        return userDao.queryAll();
    }

    @Override
    public boolean addOne(User user) {
        int result = userDao.insertByUser(user);
        if (user.getRoles()!=null){
            for (Role role:user.getRoles()){
                UserRole userRole = new UserRole();
                userRole.setRoleId(role.getRoleId());
                userRole.setRoleId(user.getUserId());
                userRoleDao.insert(userRole);
            }
        }
        return result>0;
    }

    @Override
    public boolean removeOne(User user) {
        int result = userDao.deleteByUser(user);
        return result>0;
    }

    @Override
    public boolean modifyOne(User user) {
        int result = userDao.updateByUser(user);
        return result>0;
    }

    @Override
    public boolean removeSome(Set<Integer> userIds) {
        int result = userDao.deleteByIds(userIds);
        return result>0;
    }

    @Override
    public Set<User> getUserThatAddressLike(String address) {
        String column = "address";
        return userDao.queryByColumn(column,address);
    }

    @Override
    public Set<User> getUserWithMultiCondition(String userName, String userPhone, String roleName) {
        return userDao.queryByMultiConditions(userName,userPhone,roleName);
    }


}
