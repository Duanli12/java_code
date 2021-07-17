package com.fs.dao;

import com.fs.Utils.JdbcMysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class demo1 {
    public static void main(String[] args) throws SQLException {
        Connection conn = JdbcMysql.getConn();
        String sql = "select * from student";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
        }


    }
}
