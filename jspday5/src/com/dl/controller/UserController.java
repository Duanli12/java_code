package com.dl.controller;

import com.dl.model.User;
import com.dl.service.UserService;
import com.dl.service.impl.UserServiceImpl;
import com.mysql.jdbc.UpdatableResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @description 控制层
 * @author:duanli
 * @createDate:2020/9/4 10:11
 */
@WebServlet("/uc")
public class UserController extends HttpServlet {

    UserService us = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        String op = req.getParameter("op");
        if(op.equals("checkLogin")){
            String code = req.getParameter("code");
            String username = req.getParameter("us");
            String password = req.getParameter("pwd");
            String rm = req.getParameter("rm");
            String  info =  checkLogin(code,username,password,rm,req,resp);
            pw.write(info);
            pw.close();
        }else if(op.equals("queryCookieByAjax")){
            String userString = queryCookieByAjax(req);
            pw.write(userString);
            pw.close();
        }else if(op.equals("showUsers")){
            showUsers(req);
            req.getRequestDispatcher("/WEB-INF/user/user-list.jsp").forward(req,resp);
            pw.close();
        }
        else if(op.equals("showUser")){
            queryUserByUserIdTORequest(req);
            req.getRequestDispatcher("/WEB-INF/user/member-show.jsp").forward(req,resp);
            pw.close();
        } else if(op.equals("addUserUI")){
            req.getRequestDispatcher("/WEB-INF/user/member-add.jsp").forward(req,resp);
            pw.close();
        } else if(op.equals("addUser")){
            System.out.println(req.getParameter("username"));
            System.out.println(req.getParameter("password"));
            int a = addUser(req);
            pw.write(a+"");
            pw.close();
        }else if(op.equals("delUserByUserId")){
            int a = delUserByUserId(req);
            pw.write(a+"");
            pw.close();
        }else if(op.equals("updataUserUI")){
            queryUserByUserIdTORequest(req);
            req.getRequestDispatcher("/WEB-INF/user/member-updata.jsp").forward(req,resp);
            pw.close();
        } else if(op.equals("updataUser")){
           int a = updataUserById(req);
            pw.write(a+"");
            pw.close();
        } else if(op.equals("queryUsersByKey")){
            System.out.println("haha");
           List<User> users = queryUsersByKey(req);
           req.setAttribute("tags",users);
            System.out.println(users);
            req.setAttribute("num",users.size());
            System.out.println(users.size());
           req.getRequestDispatcher("/WEB-INF/user/user-list.jsp").forward(req,resp);
           pw.close();
        }
        /*else if(op.equals("delAll")){
            int a = delALL(req);
            pw.write(a+"");
            pw.close();
        }*/
    }

    private List<User> queryUsersByKey(HttpServletRequest req) {
        String key = req.getParameter("key");
        return us.queryUsersByKey(key);
    }



    /*private int delALL(HttpServletRequest req) {
        return us.delAll(req);
    }*/

    private int updataUserById(HttpServletRequest req) {
        return us.UpdataUserById(req);
    }

    private int delUserByUserId(HttpServletRequest req) {
        return us.delUserByUserId(req);
    }

    private int addUser(HttpServletRequest req) {
        return us.addUser(req);


    }

    private void queryUserByUserIdTORequest(HttpServletRequest req) {
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = us.queryUserByUserIdTORequest(userId);
        req.setAttribute("user",user);

    }

    private void showUsers(HttpServletRequest req) {
        List<User>  userList = us.showUsers(req);
        System.out.println(userList);
        req.setAttribute("tags",userList);
        req.setAttribute("num",userList.size());//保存数据的数量

    }

    private String queryCookieByAjax(HttpServletRequest req) {
        String userString = us.queryCookieByAjax(req);
        return userString;
    }


    public String checkLogin(String code,String username,String password,String rm,HttpServletRequest req, HttpServletResponse resp){

        String info = us.checkLogin(code,username,password,rm,req,resp);
        return info;
    }
}

