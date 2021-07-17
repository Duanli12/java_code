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

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/8 19:04
 */
@WebServlet("/dt2")
public class dateTest2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到字符串日期
        String a = req.getParameter("d");
        //将该字符串转换为date类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date b = sdf.parse(a);
            System.out.println("jsp传来的时间字符串");
            System.out.println(b);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
    }


}
