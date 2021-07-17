package com.dl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static Connection conn;
    private static Properties pro = null;

    static {
        InputStream in = null;
        try {

            pro = new Properties();
            in = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("jdbcparame.properties");
            pro.load(in);
            String driverName = pro.getProperty("driverName");
           // System.out.println("driverName:" + driverName);
            Class.forName(driverName);
        } catch (Exception e) {
            System.out.println("驱动加载异常");
            e.printStackTrace();
        } finally {
            try {
                assert in != null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static Connection getConnection() {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(pro.getProperty("url"),
                        pro.getProperty("name"), pro.getProperty("password"));
               // System.out.println("连接ok");
            }
        } catch (SQLException e) {
            System.out.println("数据库连接失败：" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    public static void cloesDbRes(Connection conn, Statement statenmt, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statenmt != null) {
                statenmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {

        }

    }

}
