package com.dl.dao;

import com.dl.Application;
import com.dl.pojo.RoleInfo;
import com.dl.pojo.UserDetail;
import com.dl.pojo.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserInfoDaoTest {
    @Autowired
    UserInfoDao userInfoDao;
    //新增方法
    @Test
    public void add(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("aql");
        userInfo.setUserPwd("123");

        //用户详细表的信息
        UserDetail userDetail = new UserDetail();
        userDetail.setTel("18900008800");
        userDetail.setEmail("dj@qq.com");
        userDetail.setJoinTime(new Date());
        userDetail.setStatus(0);
        userDetail.setUrl("diojo");

        //角色表的信息
       RoleInfo r = new RoleInfo();
        r.setrName("管理员");
        r.setJoinTime(new Date());

        RoleInfo r1 = new RoleInfo();
        r1.setrName("教师");
        r1.setJoinTime(new Date());

        List<RoleInfo> roleInfoList = new ArrayList<>();
        roleInfoList.add(r);
        roleInfoList.add(r1);

        userInfo.setRoleInfoList(roleInfoList);
        userInfo.setUserDetail(userDetail);
        //新增
        userInfoDao.save(userInfo);
    }

    //修改
    @Test
    public void update(){
        //查询要修改的实体对象
        UserInfo userInfo = userInfoDao.findById(1).get();
        userInfo.setUserName("df");
        userInfo.setUserPwd("321");
        //修改
        userInfoDao.save(userInfo);
    }

    //删除
    @Test
    public void del(){
        //通过id删除
        userInfoDao.deleteById(4);
    }

    //查询
    @Test
    public void select(){
        //查询所有
        List<UserInfo> list =  userInfoDao.findAll();
        for (UserInfo u:list){
            System.out.println(u.toString());
        }
    }



}