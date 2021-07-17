package com.dl.utils;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName JdbcMysql
 * @Description 工具类：完成数据库的一些连接操作(最好使用单例模式)
 * @Author ss
 * @Date 2020/9/1 10:34
 * @Version 1.0
 */
public class JdbcMysql {
	private static Properties p = null;
	private static InputStream in = null;

	//1.私有化构造函数
	private JdbcMysql(){

	}
	//2.因为现在使用饿汉单例模式,所以立马声明一个JdbcMysql对象，注意修饰符为 private final static
	private  final  static JdbcMysql jdbcMysqlObject = new JdbcMysql();
	//3.声明一个方法来获取jdbcMysqlObject对象
	public  static JdbcMysql returnOnlyObject(){
		return  jdbcMysqlObject;
	}
	//加载配置文件的信息(将代码写在静态代码块里面)
	static {
	 in =  JdbcMysql.class.getClassLoader().getResourceAsStream("com/dl/jdbc.properties");
	p = new Properties();
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
	public  static Connection getCon(){
		Connection con = null;
		//1.得到连接池的数据源
		BasicDataSource dataSource = new BasicDataSource();
		//2.配置四大参数在数据库里面
		dataSource.setDriverClassName(p.getProperty("driverclass"));
		dataSource.setUrl(p.getProperty("url"));
		dataSource.setUsername(p.getProperty("user"));
		dataSource.setPassword(p.getProperty("password"));
		//还可以配置一些连接池的信息
		//设置连接池里面的最大活跃连接数
		dataSource.setMaxActive(20);
		//设置最大的等待时间
		dataSource.setMaxWait(20);
		//3.返回连接
		try {
			 con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			return  con;
		}
		//在连接池里面的close:将连接归还给池
		//con.close();;
	}
	//测试一下
	public static void main(String[] args) {
		Connection c = JdbcMysql.getCon();
		System.out.println(c);
	}
	//关闭各种资源
	public  static void close(Statement st, PreparedStatement ps, ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(st!=null){
				st.close();
			}
			if(ps!=null){
				ps.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}




