package com.dl.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/1
 */
@WebServlet("/csl")
public class countServlet1 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        ServletContext servletContext = this.getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        if( servletContext.getAttribute("count") == null) {
            count = 1;
            servletContext.setAttribute("count", count);
        }else {
            servletContext.setAttribute("count", count+1);
        }

        PrintWriter pw =response.getWriter();
        pw.write("<!DOCTYPE html>" +
                "<html>" +"<head>" + "<meta charset=\"UTF-8\">\r\n" +
                "<title>登陆网页次数统计</title>\r\n" +
                "</head>\r\n" +
                "<body>");
        pw.print("<h2>");
        pw.print("您是第 "+ servletContext.getAttribute("count")+"位访客");
        pw.print("<h2>");
        pw.print("</body>\r\n" +
                "</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
