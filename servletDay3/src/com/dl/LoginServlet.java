package com.dl;

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
 * @createDate:2020/9/1
 */
@WebServlet("/ls")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String info = (String) req.getAttribute("info");
        if (info==null){
            info=" ";
        }
        PrintWriter pw = resp.getWriter();
        pw.write("欢迎登录<html><br/>"+"<font color='red'>"+info+"</font><br/>"+
                "<form action='cls'>"+
                "用户名<input type='text' name='username' ><br/>"+
                "密码<input type='password' name='password' ><br/>"+
                "<input type='submit' name='tijiao' value='登录' >"+
                "</form></html>");
    }


}
