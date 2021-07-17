package com.fs.controller;

import com.fs.service.StuService;
import com.fs.service.sericeImpl.StuServiceImpl;

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
 * @createDate:2020/9/10 19:17
 */
@WebServlet("/pc")
public class PageCotroller extends HttpServlet {
    StuService us = new StuServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        //拿到操作码
        String op = req.getParameter("op");
        /*在这儿去调用业务层，判断是否有指定的Cookie*/

        if(op==null){
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }else if(op.equals("index")){
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }else if(op.equals("register")){
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }
}
