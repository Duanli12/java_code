package com.hqyj.dao;


import com.hqyj.Application;
import com.hqyj.pojo.RoleInfo;
import com.hqyj.pojo.UserDetail;
import com.hqyj.pojo.UserInfo;
import com.hqyj.service.UserInfoService;
import com.hqyj.util.MdFive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserInfoDaoTest {

    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    UserInfoService userInfoService;
    //新增方法
    @Test
    public void add(){
        //用户表的
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("aaa");
        userInfo.setUserPwd("123");

        //用户详细表的信息
        UserDetail userDetail = new UserDetail();
        userDetail.setTel("12345658917");
        userDetail.setEmail("djlj@sdf.com");
        userDetail.setJoinTime(new Date());
        userDetail.setStatus(0);
        userDetail.setUrl("111");

        //角色 表的信息
        RoleInfo r = new RoleInfo();
        r.setrName("管理员");
        r.setJoinTime(new Date());

        RoleInfo r1 = new RoleInfo();
        r1.setrName("教师");
        r1.setJoinTime(new Date());

        List<RoleInfo> list = new ArrayList<>();
        list.add(r);
        list.add(r1);
        userInfo.setRoleInfoList(list);

        userInfo.setUserDetail(userDetail);

        userInfoDao.save(userInfo);
    }
    //修改
    @Test
    public void update(){
        //查询要修改的实体对象，
       UserInfo userInfo= userInfoDao.findById(3).get();

       //修改用户详细实体对象

        userInfo.getUserDetail().setStatus(0);
       //修改
        userInfoDao.save(userInfo);
    }

    //删除
    @Test
    public void del(){
        //根据主键id删除
        userInfoDao.deleteById(3);
    }
    //查询
    @Test
    public void select(){
        //查询所有
 /*      List<UserInfo> list= userInfoDao.findAll();
       for(UserInfo u:list){
           System.out.println(u.getUserDetail().getEmail());
       }*/
    }

    //发送邮件
    @Test
    public void sendEmail() {
        HttpServletRequest request =null;
        userInfoService.sendEmail("2395211420@qq.com", request);
    }

    @Test
    public void updatePassword(){
        UserInfo userInfo = userInfoDao.findById(2).get();
        MdFive mdFive = new MdFive();
        String encrypt = mdFive.encrypt("123",userInfo.getUserName());
        userInfo.setUserPwd(encrypt);
        userInfoDao.save(userInfo);
    }

}