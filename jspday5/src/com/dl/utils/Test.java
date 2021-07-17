package com.dl.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName Test
 * @Description TODO
 * @Author ss
 * @Date 2020/9/1 10:24
 * @Version 1.0
 */
public class Test {
	public static void main(String[] args) {
		try {
			//加载驱动-->在DriverManager注册对象
			Class.forName("com.mysql.jdbc.Driver");
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/je200701","root","112476");
				System.out.println(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
