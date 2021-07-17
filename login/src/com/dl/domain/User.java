package com.dl.domain;

/**
 * @description
 * @author:duanli
 * @createDate:2020/8/31
 */
public class User {
    private String uname;
    private String password;

    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
