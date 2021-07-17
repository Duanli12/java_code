package com.hqyj.dl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.dl.dao.UserDao;
import com.hqyj.dl.dao.UserRoleDao;
import com.hqyj.dl.pojo.Role;
import com.hqyj.dl.pojo.User;
import com.hqyj.dl.pojo.UserRole;
import com.hqyj.dl.service.UserService;
import com.hqyj.dl.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/15 14:06
 */

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserRoleDao userRoleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserRoleDao userRoleDao) {

        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }


    @Override
    public List<User> findByAddressUser(String column, String value) {
        List<User> users = userDao.findByAddressUser(column, value);
        System.out.println(users);
        return users;
    }

    @Override
    public List<User> getAll() {

        return userDao.queryAll();
    }

    @Override
    public User getOneByUserId(Integer userId) {
        User user = userDao.queryById(userId);
        System.out.println(user);
        return user;
    }

    @Override
    public User getOneByUsername(String username) {

        return userDao.queryByUsername(username);
    }

    @Override
    public PageInfo<User> getUserByPage(Integer currentPage, Integer pageSize) {
        //准备拦截mybatis后续的查询
        PageHelper.startPage(currentPage, pageSize);
        List<User> userList = userDao.queryAll();
        System.out.println(userList.size());
        //PageHelper内部计算总共有多少页
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public boolean addOne(User user) {
        int result = userDao.insertByUser(user);
        //判断添加的用户的角色信息是否为空
        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(role.getRoleId());
                userRole.setRoleId(user.getUserId());
                userRoleDao.insert(userRole);
            }
        }
        return result > 0;
    }

    @Override
    public boolean removeOne(User user) {
        int result = userDao.deleteByUser(user);
        return result > 0;
    }

    @Override
    @Transactional//注明当前的方法事务性执行
    public boolean modifyOne(User user) {
        //salt的选择这里使用的是用户名，账号
        String hashedPassword = MD5Utils.md5Hash(user.getPassword(), user.getUsername());
        user.setPassword(hashedPassword);
        int result = userDao.updateByUser(user);
        if (true) {
            throw new RuntimeException();
        }
        return result > 0;
    }

    @Override
    public boolean removeSome(Set<Integer> userIds) {
        int result = userDao.deleteByIds(userIds);
        return result > 0;
    }

    @Override
    public Set<User> getUserThatAddressLike(String address) {
        String column = "address";
        return userDao.queryByColumn(column, address);
    }

    @Override
    public Set<User> getUserWithMultiCondition(String userName, String userPhone, String roleName) {
        return userDao.queryByMultiConditions(userName, userPhone, roleName);
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> result = new HashMap<>();
        //获取shiro系统认为的当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //判断是否已经认证过，认证过就不需要再次认证
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //让shiro系统记住你，会保存用户身份信息到浏览器中，
            // 下次如果需要shiro身份授权时，会使用该身份
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                //shiro告诉查无此人
                result.put("code", -2);
                result.put("message", "用户输入错误");
                return result;
            } catch (ArithmeticException e) {
                //shiro告诉我们出现认证异常
                result.put("code", -1);
                result.put("message", "登录失败");
                return result;
            } catch (IncorrectCredentialsException e) {
                //shiro告诉我们密码错误
                result.put("code", -3);
                result.put("message", "密码错误");
                return result;
            }
        }
        User user = (User) currentUser.getSession().getAttribute("user");
        result.put("code", 0);
        result.put("message", user.getName() + "登录成功");
        result.put("user", user);
        return result;
    }

    @Override
    public User logout() {
        Subject currentUser = SecurityUtils.getSubject();
        User user = (User) currentUser.getSession().getAttribute("user");
        currentUser.logout();
        return user;
    }


}
