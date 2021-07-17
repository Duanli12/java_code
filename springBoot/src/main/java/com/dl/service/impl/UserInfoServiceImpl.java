package com.dl.service.impl;

import com.dl.dao.RoleInfoDao;
import com.dl.dao.UserInfoDao;
import com.dl.pojo.RoleInfo;
import com.dl.pojo.UserDetail;
import com.dl.pojo.UserInfo;
import com.dl.pojo.UserInfoParam;
import com.dl.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName UserInfoServiceImpl
 * @description
 * @author:duanli
 * @createDate:2020.10.13 15:38
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private RoleInfoDao roleInfoDao;


    @Override
    public HashMap<String,Object> login(UserInfo userInfo, HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<>();
       /* UserInfo user =   userInfoDao.findByUserNameAndUserPwd(userInfo.getUserName(),userInfo.getUserPwd());*/
        UserInfo user = userInfoDao.findByUserName(userInfo.getUserName());
        if (user!=null){
            if (user.getUserPwd().equals(userInfo.getUserPwd())){
                if(user.getUserDetail().getStatus()==0){
                    //创建session对象
                    HttpSession session = request.getSession();
                    //存入session
                    session.setAttribute("userInfo",user);
                    map.put("code",0);
                    map.put("message","登录成功");
                }else {
                    map.put("message","用户已经被禁用");
                }

            }else {
                map.put("message","密码错误");
            }

        }else {
            map.put("message","该用户不存在");
        }
        return map ;
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    @Override
    public boolean update(Integer id) {
        try {
            UserInfo  user = userInfoDao.findById(id).get();
            if(user!=null){
                Integer status = user.getUserDetail().getStatus();
                if(status==0){
                    user.getUserDetail().setStatus(1);
                }else{
                    user.getUserDetail().setStatus(0);
                }
                userInfoDao.save(user);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<RoleInfo> selectRole() {
        return roleInfoDao.findAll();
    }

    @Override
    public boolean add(UserInfoParam userInfoParam) {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(userInfoParam.getUserName());
            userInfo.setUserPwd(userInfoParam.getUserPwd());

            //用户详细表的信息
            UserDetail userDetail = new UserDetail();
            userDetail.setTel(userInfoParam.getTel());
            userDetail.setEmail(userInfoParam.getEmail());
            userDetail.setJoinTime(new Date());
            userDetail.setStatus(0);
            userDetail.setUrl(userInfoParam.getUrl());

            List<RoleInfo> roleInfoList = new ArrayList<>();

            //角色表的信息
            if (!userInfoParam.getRoleId().equals("")) {
                //去除id
                String r[] = userInfoParam.getRoleId().split(",");
                for (int i = 0; i < r.length; i++) {

                    RoleInfo r1 = roleInfoDao.findById(Integer.parseInt(r[0])).get();
                    roleInfoList.add(r1);
                }
            }

            //添加用户的详细信息对象
            userInfo.setUserDetail(userDetail);
            //添加用户的角色对象
            userInfo.setRoleInfoList(roleInfoList);
            //新增
            userInfoDao.save(userInfo);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserInfo selectById(Integer id) {
        return userInfoDao.findById(id).get();
    }


}
