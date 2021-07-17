package com.dl.dao;

import com.dl.domain.User;
import com.dl.utils.JDBCUtils;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description
 * @author:duanli
 * @createDate:2020/8/31
 */
public class UserDao {
    public User login(String username, String password){
        User u = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from users where uname=? and password=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                u = new User();
                u.setUname(resultSet.getString("name"));
                u.setPassword(resultSet.getString("password"));
                System.out.println("登录成功");
            }
            else{
                System.out.println("用户名或者密码错误");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }


}
