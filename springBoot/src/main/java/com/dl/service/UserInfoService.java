package com.dl.service;

import com.dl.pojo.RoleInfo;
import com.dl.pojo.UserInfo;
import com.dl.pojo.UserInfoParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName UserInfoService
 * @descriptiony7y
 * @author:duanli
 * @createDate:2020.10.13 15:36
 */
public interface UserInfoService {
    HashMap<String,Object> login(UserInfo userInfo, HttpServletRequest request);
    List<UserInfo> findAll();//查询所有


    boolean update(Integer id);//修改

    List<RoleInfo> selectRole();//查询所有角色

    boolean add(UserInfoParam userInfoParam);

    UserInfo selectById(Integer id);
}
