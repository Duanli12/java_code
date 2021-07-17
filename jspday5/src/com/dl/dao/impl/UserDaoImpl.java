package com.dl.dao.impl;

import com.dl.dao.UserDao;
import com.dl.model.User;
import com.dl.utils.JdbcMysql;
import com.dl.utils.Page;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/4 12:47
 */
public class UserDaoImpl implements UserDao {
    private  JdbcMysql jm = JdbcMysql.returnOnlyObject();
    private Connection con = JdbcMysql.getCon();
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    @Override
    public User queryUserByUserNameAndPassword(String username, String password) {
        try {
            ps = con.prepareStatement("select  * from users where  username=? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs.next()){
                User u = new User(rs.getInt(1),rs.getString(2),rs.getString(3));
                return u;
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
    public User queryUserByUserId(int userId) {
        try {
            ps = con.prepareStatement("select  * from users where  userId=?");
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            rs.next();
            User u = new User(rs.getInt(1),rs.getString(2),rs.getString(3));
            return u;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcMysql.close(null,ps,rs);
        }
        return null;
    }

    @Override
    public List<User> showUsers() {
        List<User> userList = new ArrayList<>();
        try {
            ps = con.prepareStatement("select  * from users ");
            rs = ps.executeQuery();
            while (rs.next()){
                User u = new User(rs.getInt(1),rs.getString(2),rs.getString(3));
                userList.add(u);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcMysql.close(null,ps,rs);
        }
        return null;
    }

    @Override
    public int addUser(String username, String password) {
        try {
            ps = con.prepareStatement("insert into users(username,password) values (?,?)");
            ps.setString(1,username);
            ps.setString(2,password);
            int a = ps.executeUpdate();
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcMysql.close(null,ps,null);
        }
        return 0;
    }

    @Override
    public int delUserByUserId(int userId) {
        try {
            ps = con.prepareStatement("delete from users where userId = ?");
            ps.setInt(1,userId);
            int a = ps.executeUpdate();
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcMysql.close(null,ps,null);
        }
        return 0;
    }

    @Override
    public int UpdataUserById(String username, String password, int userId) {
        try {
            ps = con.prepareStatement("update users set username=?,password=? where userId=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3,userId);
            int a = ps.executeUpdate();
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcMysql.close(null, ps, null);
        }
        return 0;
    }


    @Override
    public List<User> queryUsersByKey(String key) {
        List<User> userList = new ArrayList<>();
        try {
            ps = con.prepareStatement("select  * from users where  username like ?");
            ps.setString(1,key);
            rs = ps.executeQuery();
            while (rs.next()){
                User u = new User(rs.getInt(1),rs.getString(2),rs.getString(3));
                userList.add(u);
            }
            return  userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcMysql.close(null,ps,rs);
        }
        return null;
    }

    @Override
    public int queryAllUserNum() {

        try {
            ps = con.prepareStatement("select count(*)  from users ");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcMysql.close(null,ps,rs);
        }
        return 0;
    }

    @Override
    public List<User> showUsersByPage(Page p) {
        List<User> userList = new ArrayList<>();
        try {
            /*分页查询语句select * from users limit (页数-1)*每页显示的条数 */
            ps = con.prepareStatement("select  * from users limit ?,? ");
            ps.setInt(1,(p.getNowPage()-1)*p.getPageNum());
            ps.setInt(2,p.getPageNum());
            rs = ps.executeQuery();
            while (rs.next()){
                User u = new User(rs.getInt(1),rs.getString(2),rs.getString(3));
                userList.add(u);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcMysql.close(null,ps,rs);
        }
        return null;

    }
}
