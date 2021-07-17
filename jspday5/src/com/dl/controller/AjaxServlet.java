package com.dl.controller;

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
 * @createDate:2020/9/3 18:19
 */
@WebServlet("/as")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String code = req.getParameter("code");
        String rightcode = (String) req.getSession().getAttribute("rightCode");
        code = code.toLowerCase();
        rightcode = rightcode.toLowerCase();
        PrintWriter pw = resp.getWriter();
        if(code.equals(rightcode)){
            pw.write("验证通过");
        }else {
            pw.write("验证失败");
        }
        pw.close();
    }
}
