package com.fs.Utils;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcMysql {
	private static Connection conn = null;
	private static InputStream is = null;
	private static Properties prop = null;
	//1.私有化构造函数
	private JdbcMysql(){

	}

	//2.因为现在使用饿汉单例模式，所以立马声明一个JDBCMysql对象，注意修饰符为private　final　static
	private final static JdbcMysql jdbcMysqlObject = new JdbcMysql();

	//3.声明一个方法来获取jdbcMysqlObject对象
	public static JdbcMysql returnOnlyObject(){
		return jdbcMysqlObject;
	}
	//加载配置文件的信息（将代码卸载静态代码块里面）
	static {
		is = JdbcMysql.class.getClassLoader().getResourceAsStream("db.properties");
		prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {
			System.out.println("db.properties序列化失败");
		}finally {

			try {
				if (is != null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
	//使用连接池的方式连接数据库，并返回连接
	public static Connection getConn(){
		Connection conn = null;
		//1.得到连接池的数据源
		BasicDataSource dataSource = new BasicDataSource();

		/*2.配置四大参数在数据库里面*/
		//1.加载驱动
		dataSource.setDriverClassName(prop.getProperty("driver"));
		//2.URL
		dataSource.setUrl(prop.getProperty("url"));
		//3. username
		dataSource.setUsername(prop.getProperty("user"));
		//4.password
		dataSource.setPassword(prop.getProperty("password"));

		//返回连接
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("数据库连接失败！");
		}finally {
			return conn;
		}

		//在连接池里面的close：将连接归还给池
		//conn.close();
	}



	public static void close(ResultSet rs, PreparedStatement preparedStatement, Statement st, Connection conn){

		try {
			if (rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (preparedStatement!=null){
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (st!=null){
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

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
