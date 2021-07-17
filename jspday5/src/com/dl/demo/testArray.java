package com.dl.demo;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/9 16:49
 */
@WebServlet("/testArray")
public class testArray extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        PrintWriter pw = res.getWriter();

       //获取数组的方式 getParameterValues("键值[]");
        Object[] os = req.getParameterValues("array1[]");
        for (Object o:os){
            System.out.println(o);
        }
        Map<String,String> map1 = new HashMap<>();
        map1.put("info","保存的第一个信息");
        map1.put("info1","保存的第二个信息");
        String mapString = new Gson().toJson(map1);
        String osString = new Gson().toJson(os);
        //将map转换成json字符串
        //pw.write(mapString);
        //将数组os转换成json字符串
        pw.write(osString);
        pw.close();

    }
}
