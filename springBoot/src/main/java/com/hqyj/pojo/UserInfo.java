package com.hqyj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity //必选注解，表示该pojo类对应数据库表
@Table//user_info
public class UserInfo {

    @Id//数据库的主键列名
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uId;

    //Column 列名注解，非主键列  length = 30 是可选参数，表示长度是30.不定义的默认是255
    //unique 唯一约束
    @Column(length = 30,unique = true)
    private String userName;

    @Column
    private String userPwd;

    public UserDetail getUserDetail() {

        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {

        this.userDetail = userDetail;
    }

    //一对一的关系配置
    //CascadeType.REMOVE  级联删除
    //CascadeType.MERGE  级联修改和添加
    //CascadeType.PERSIST 级联查询
    //CascadeType.All  拥有所有权限
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST})
    //JoinColumn  配置一对一表关系的外键
    //name="主表关联附表的外键id"
    //referencedColumnName="附表的主键id属性"
    @JoinColumn(name="ud_id",referencedColumnName = "udId")
    @JsonIgnoreProperties(value="userInfo")//解析无线地柜
    private UserDetail userDetail;
    //ManyToMany 多对多关系的配置注解
    //JoinTable 是配置多对多关系的注解
    //JoinTable(name="中间表的表名",)
    // @JoinColumn(name = "主表(当前pojo类对应的表)的主键列名",
    //    // referencedColumnName = "中间表的与主表关联的列名对应的实体属性")}
    //inverseJoinColumns = {
    //     @JoinColumn(name = "与主表对应多对多关联的表的主键列名",
    //     referencedColumnName = "中间表的 多对多关系表的对应的列名的实体属性 ")}
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role_relation", joinColumns = {
            @JoinColumn(name = "u_id", referencedColumnName = "uId")}, inverseJoinColumns = {
            @JoinColumn(name = "r_id", referencedColumnName = "rId")})
    @JsonIgnoreProperties(value="userInfoList")
    private List<RoleInfo> roleInfoList;

    public List<RoleInfo> getRoleInfoList() {
        return roleInfoList;
    }

    public void setRoleInfoList(List<RoleInfo> roleInfoList) {
        this.roleInfoList = roleInfoList;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "uId=" + uId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}
