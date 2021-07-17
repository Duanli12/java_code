package com.dl.controller;

import com.dl.model.User;
import com.dl.service.UserService;
import com.dl.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/7 9:50
 */
@WebServlet("/pc")
public class PageController extends HttpServlet {
    UserService us = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        //拿到操作码
        String op = req.getParameter("op");
        /*在这儿去调用业务层，判断是否有指定的Cookie*/
        //User u = us.queryCookie(req);
        //将u存到req里面，以便于转发后的页面使用EL表达式
        //req.setAttribute("user",u);
        if(op==null){
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
        }else if(op.equals("index")){
            req.getRequestDispatcher("/WEB-INF/system/index.jsp").forward(req,resp);
        }else if(op.equals("welcome")){
            //这里可以准备很多数据，ip地址，主机名，端口号.... 保存到req,可以在welcome里面用EL表达式获取
            req.getRequestDispatcher("/WEB-INF/system/welcome.jsp").forward(req,resp);
        }

    }
}
