package com.dl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description 创建ServletContext
 * @author:duanli
 * @createDate:2020/9/1
 */
@WebServlet("/csc")
public class CreateServletContext extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        //获取servletcontext
        ServletContext application = req.getServletContext();
        //在servletcontext存储数据
        application.setAttribute("name","小丑");
        pw.write("servletcontext存储值成功");
        pw.close();
    }
}
