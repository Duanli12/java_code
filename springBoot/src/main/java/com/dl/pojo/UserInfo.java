package com.dl.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserInfo
 * @description
 * @author:duanli
 * @createDate:2020.10.13 13:36
 */
@Entity //必选注解，表示该pojo类对呀数据库表
@Table //默认在数据库里生成一张user_info表
public class UserInfo  {
    @Id //数据库的主键列名
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Jpa 通用策略生成器
    private Integer id;

    // Column列名注解，非主键  length = 30 是可选参数  表示长度是30 不定义默认是255
    // unique = true可选参数 唯一约束
    @Column(length = 30,unique = true)
    private String userName;

    @Column
    private String userPwd;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role_relation", joinColumns = {
            @JoinColumn(name = "u_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "r_id", referencedColumnName = "rId")})
    private List<RoleInfo> roleInfoList;



    //一对一的关系配置
    //CascadeType.REMOVE   级联删除配置
    //CascadeType.MERGE    级联修改和添加
    //CascadeType.PERSIST     级联查询
    //CascadeType.ALL   拥有所有权限
    @OneToOne(cascade ={CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST} )
    //JoinColumn  配置一对一表关系的外键
    //name="主表关联附表的外键id"
    //referencedColumnName="附表的主键id属性"
    @JoinColumn(name = "ud_id" ,referencedColumnName ="udId" )
    private UserDetail userDetail;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", roleInfoList=" + roleInfoList +
                ", userDetail=" + userDetail +
                '}';
    }

    public List<RoleInfo> getRoleInfoList() {
        return roleInfoList;
    }

    public void setRoleInfoList(List<RoleInfo> roleInfoList) {
        this.roleInfoList = roleInfoList;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public UserInfo() {
    }

}
