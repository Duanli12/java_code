package com.fs.controller;

import com.fs.model.Student;
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
 * @createDate:2020/9/10 16:54
 */
@WebServlet("/sc")
public class StuController extends HttpServlet {
    StuService ss = new StuServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        String op = req.getParameter("op");
        if (op.equals("login")){

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String code = req.getParameter("code");
            String info = login(username,password,code,req,resp);
            pw.write(info);
            pw.close();
        }else if (op.equals("register")){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String info = register(username,password,req,resp);
            pw.write(info);
            pw.close();
        }
    }

    private String register(String username, String password, HttpServletRequest req, HttpServletResponse resp) {
        String info = ss.register(username,password,req,resp);
        return info;
    }

    private String login(String username, String password, String code, HttpServletRequest req, HttpServletResponse resp) {
        String info = ss.login(username,password,code,req,resp);
        return info;
    }




}
