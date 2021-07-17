package com.dl.service;

import com.dl.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface UserService {

    String checkLogin(String code, String username, String password, String rm, HttpServletRequest req, HttpServletResponse resp);

    User queryCookie(HttpServletRequest req);

    String queryCookieByAjax(HttpServletRequest req);

    List<User> showUsers(HttpServletRequest req);


    User queryUserByUserIdTORequest(int userId);

    int addUser(HttpServletRequest req);

    int delUserByUserId(HttpServletRequest req);

    int UpdataUserById(HttpServletRequest req);

    List<User> queryUsersByKey(String key);

    /*int delAll(HttpServletRequest );*/
}
