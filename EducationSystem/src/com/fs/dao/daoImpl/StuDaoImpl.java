package com.fs.dao.daoImpl;

import com.fs.Utils.JdbcMysql;
import com.fs.dao.StuDao;
import com.fs.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/10 16:35
 */
public class StuDaoImpl implements StuDao {
    private Connection conn = JdbcMysql.getConn();
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    @Override
    public Student queryUserByUserNameAndPassword(String username, String password) {
        try {
            ps = conn.prepareStatement("select * from student where stu_name=? and stu_pwd=?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs.next()){
                Student st = new Student(rs.getInt(1),rs.getString(2),rs.getString(3));
                System.out.println(st);
                return st;
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcMysql.close(null,ps,rs);
        }

        return null;
    }

    @Override
    public void register(String username, String password) {
        try {
            ps = conn.prepareStatement("insert into student(stu_name,stu_pwd) values (?,?)");
            ps.setString(1,username);
            ps.setString(2,password);
            int a = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcMysql.close(null,ps,null);
        }


    }



}
