package com.dl.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/8 18:46
 */
@WebServlet("/dt")
public class dateTest  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = new Date();
        System.out.println("当前时间");
        System.out.println(date);
        req.setAttribute("t",date);
        req.getRequestDispatcher("/date.jsp").forward(req,resp);




    }
}
