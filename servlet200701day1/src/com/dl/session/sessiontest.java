package com.dl.session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description
 * @author:duanli
 * @createDate:2020/8/31
 */
@WebServlet("/sge")
public class sessiontest extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object o = session.getAttribute("name");
        System.out.println("得到了session存储的键值对name："+o);
    }

}
