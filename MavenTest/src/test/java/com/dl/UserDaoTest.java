package com.dl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao;
    @Before
    public void setUp() throws Exception {
        userDao = new UserDao();
        User user = new User();
        user.setId(1);
        user.setName("张三");
        userDao.add(user);
        User user2 = new User();
        user.setId(2);
        user.setName("李四");
        userDao.add(user2);

    }


    @Test
    public void add() {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId(1);
        user.setName("张三");
        userDao.add(user);
        List<User> userList = userDao.getAll();
        String name = userList.get(0).getName();
        assertEquals(name,"张三");
    }

    @Test
    public void delete() {
        User user = new User();
        user.setId(1);
        userDao.delete(user);
        List<User> userList = userDao.getAll();
        assertTrue(userList.isEmpty());
        assertFalse(userList.contains(user));
    }

    @Test
    public void update(){
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId(1);
        user.setName("张三");
        userDao.add(user);

        User user2 = new User();
        user2.setId(1);
        user2.setName("李四");
        userDao.update(user2);
        List<User> userList = userDao.getAll();
        for (User user1:userList){
            if (user1.getId()==1){
                String name = user.getName();
                assertEquals(name,"李四");
            }
        }

    }

    @After
    public void tearDown() throws Exception {
    }
}