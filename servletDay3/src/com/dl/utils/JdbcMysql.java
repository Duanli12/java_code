package com.dl.utils;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @description 连接数据库工具类
 * @author:duanli
 * @createDate:2020/9/1
 */
public class JdbcMysql {
    private static Properties p = null;
    private static InputStream in = null;
    //私有化构造函数
    private JdbcMysql(){

    }
    //使用饿汉单例模式，所以立马声明一个JdbcMysql对象
    private final static JdbcMysql jdbcMysqlObject = new JdbcMysql();
    //声明一个方法来获取jdbcMysqlObject对象
    public static JdbcMysql returnOnlyObject(){
        return jdbcMysqlObject;
    }
    //加载配置文件的信息
    static {
        in =JdbcMysql.class.getClassLoader().getResourceAsStream("jdbc.properties");
        p=new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(in!=null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //使用连接池的方式连接数据库，并返回连接
    public static Connection getConnection(){
        Connection conn = null;
        //得到连接池的数据源
        BasicDataSource dataSource = new BasicDataSource();
        //配置四大参数在数据库里面
        dataSource.setDriverClassName(p.getProperty("driverName"));
        dataSource.setUrl(p.getProperty("url"));
        dataSource.setUsername(p.getProperty("user"));
        dataSource.setPassword(p.getProperty("password"));
        //还可以配置一下连接池的信息
        //设置连接池里面的最大活跃连接数
       /* dataSource.setMaxActive(20);
        //设置最大等待时间
        dataSource.setMaxWait(20);*/
        //返回连接
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return conn;
        }
    }
    //测试
    public static void main(String[] args) {
        Connection c = JdbcMysql.getConnection();
        System.out.println(c);
    }
    public static void Close(Statement st, PreparedStatement ps, ResultSet rs){
        try {
            if(rs!=null){
                rs.close();

            }
            if(ps!=null){
                ps.close();

            }if(st!=null){
                st.close();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
