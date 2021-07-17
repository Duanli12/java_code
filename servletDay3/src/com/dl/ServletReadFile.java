package com.dl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description  读文件
 * @author:duanli
 * @createDate:2020/9/1
 */
@WebServlet("/srf")
public class ServletReadFile extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        BufferedReader br=new BufferedReader(new FileReader("D:\\桌面\\a.txt"));
        String a = br.readLine();
        pw.write(a);
        br.close();
        pw.close();
    }
}
