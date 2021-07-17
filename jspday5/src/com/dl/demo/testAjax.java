package com.dl.demo;

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
 * @createDate:2020/9/3 15:01
 */
@WebServlet("/tt")
public class testAjax extends HttpServlet {
    /*在ajax的请求内，一定不要进行转发或者重定向*/
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("我来了！！！");
        //接受ajax传过来的数据
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        //返回数据给ajax
        PrintWriter pw = resp.getWriter();
        pw.write("hhhh");
        pw.close();
    }
}
