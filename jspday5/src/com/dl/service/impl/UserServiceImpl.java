package com.dl.service.impl;

import com.dl.dao.UserDao;
import com.dl.dao.impl.UserDaoImpl;
import com.dl.model.User;
import com.dl.service.UserService;
import com.dl.utils.Page;
import com.google.gson.Gson;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/4 10:22
 */
public class UserServiceImpl implements UserService {
    UserDao ud = new UserDaoImpl();
    @Override
    public String checkLogin(String code, String username, String password, String rm, HttpServletRequest req, HttpServletResponse resp) {
        String rightCode = (String) req.getSession().getAttribute("rightCode");
        if(rightCode.equals(code)){
            User u = ud.queryUserByUserNameAndPassword(username,password);
            if(u!=null){
                req.getSession().setAttribute("USER",u);
                if(rm.equals("yes")){
                    Cookie c = new Cookie("USERID",String.valueOf(u.getUserId()));
                    c.setMaxAge(30*24*60*60);
                    resp.addCookie(c);
                }
                return "SUCCESS";
            }else {
                return "ERROR";
            }
        }else {
            return "ERRORCODE";
        }


    }

    @Override
    public User queryCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if(cookies!=null){
            for (Cookie c: cookies ) {
                if(c.getName().equals("USERID")){
                    int userId = Integer.parseInt(c.getValue());
                    User u = ud.queryUserByUserId(userId);
                    return u;
                }
            }
        }else {
            return null;
        }
        return null;
    }

    @Override
    public String queryCookieByAjax(HttpServletRequest req) {
        Gson g = new Gson();
        Cookie[] cookies = req.getCookies();
        if(cookies!=null){
            for (Cookie c: cookies ) {
                if(c.getName().equals("USERID")){
                    int userId = Integer.parseInt(c.getValue());
                    User u = ud.queryUserByUserId(userId);

                    String uString = g.toJson(u);
                    System.out.println(uString);
                    return uString;
                }
            }
        }else {
            return g.toJson(new User(1,"",""));
        }
        return g.toJson(new User(1,"",""));
    }

    @Override
    public List<User> showUsers(HttpServletRequest req) {
        Page p = new Page();
        //获取用户点击的页数
        String nowPageString = req.getParameter("nowPage");
        //设置p的当前页数
        if (nowPageString!=null){
            p.setNowPage(Integer.parseInt(nowPageString));
        }
        //设置需要分页的总数
        int totalNum = ud.queryAllUserNum();
        p.setTotalNum(totalNum);
        //设置分多少页
        if (totalNum%p.getPageNum()==0){
            p.getPageTotal(totalNum/p.getPageNum());
        }else {
            p.getPageTotal(totalNum/p.getPageNum()+1);
        }
        //将准备好的分页信息保存到req里
        req.setAttribute("p",p);
        //查询出某页的数据
        List<User> users = ud.showUsersByPage(p);
        return users;
    }

    @Override
    public User queryUserByUserIdTORequest(int userId) {
        User u = ud.queryUserByUserId(userId);
        return u;
    }

    @Override
    public int addUser(HttpServletRequest req) {
        String  username = req.getParameter("username");
        String  password = req.getParameter("password");
        return ud.addUser(username,password);
    }

    @Override
    public int delUserByUserId(HttpServletRequest req) {
        int userId =Integer.parseInt(req.getParameter("userId")) ;
        return ud.delUserByUserId(userId);
    }

    @Override
    public int UpdataUserById(HttpServletRequest req) {
        String  username = req.getParameter("username");
        String  password = req.getParameter("password");
        int userId = Integer.parseInt(req.getParameter("userId"));
        return ud.UpdataUserById(username,password,userId);
    }

    @Override
    public List<User> queryUsersByKey(String key) {
        //构建模糊查询的字符串
        key = key + "%";
        return ud.queryUsersByKey(key);
    }


   /* @Override
    public int delAll(HttpServletRequest req) {
        String[] ids = req.getParameterValues("userId");
        for(String userId : ids){
            ud.delUserByUserId(userId);
        }
    }*/



}
