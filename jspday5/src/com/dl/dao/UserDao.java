package com.dl.dao;

import com.dl.model.User;
import com.dl.utils.Page;

import java.util.List;

public interface UserDao {
    User queryUserByUserNameAndPassword(String username, String password);
    User queryUserByUserId(int userId);

    List<User> showUsers();

    int addUser(String username, String password);

    int delUserByUserId(int userId);

    int UpdataUserById(String username, String password, int userId);

    List<User> queryUsersByKey(String key);

    int queryAllUserNum();

    List<User> showUsersByPage(Page p);
}
