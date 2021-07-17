package com.hqyj.dao;


import com.hqyj.pojo.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void selectByPrimaryKey(){
        UserInfo user=userInfoMapper.selectByPrimaryKey(1);
        System.out.println(user.getUserName());
    }

}