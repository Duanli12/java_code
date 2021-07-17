package com.hqyj.dl.service.impl;

import com.hqyj.dl.pojo.User;
import com.hqyj.dl.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring.xml")
public class UserServiceImplTest {
    @Autowired
    UserService userService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAll() {
        List<User> all = userService.getAll();
        System.out.println(all);

    }

    @Test
    public void addOne() {
    }

    @Test
    public void removeOne() {
    }

    @Test
    public void modifyOne() {
    }

    @Test
    public void removeSome() {
    }

    @Test
    public void getUserThatAddressLike() {
    }

    @Test
    public void getUserWithMultiCondition() {
    }
}