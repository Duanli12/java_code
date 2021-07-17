package com.dl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @description 写文件
 * @author:duanli
 * @createDate:2020/9/1
 */
@WebServlet("/swf")
public class ServletWriteFile extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\桌面\\a.txt"));
        bw.write("aaaaaaaaa");
        //缓存流最好要执行下一步操作（将缓存区里面的数据写到文件里面去）
        bw.flush();
        pw.write("写入文件成功");
        bw.close();
        pw.close();
    }
}
