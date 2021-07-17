package com.dl;

import com.dl.utils.JdbcMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/1
 */
@WebServlet("/cls")
public class CheckLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String uname = req.getParameter("uname");
        String password = req.getParameter("password");
        Connection conn = JdbcMysql.getConnection();
        try {
            ps = conn.prepareStatement("select * from users where uname=? and password=?");
            ps.setString(1,uname);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){
                req.getSession().setAttribute("username",uname);
                req.getRequestDispatcher("/sls").forward(req,resp);
            }else {
                req.setAttribute("info","账号或者密码错误！！！");
                req.getRequestDispatcher("/ls").forward(req,resp);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcMysql.Close(null,ps,rs);
        }
    }
}
