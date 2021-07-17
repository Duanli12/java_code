package com.dl.pojo;

import javax.persistence.*;
import java.util.Date;


/**
 * @ClassName RoleInfo
 * @description
 * @author:duanli
 * @createDate:2020.10.14 10:05
 */
@Entity
@Table
public class UserDetail  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer udId;

    @Column(length = 20)
    private String tel;

    @Column(length = 20)
    private String email;

    @Column(length = 20)
    private String url;

    @Column(length = 20)
    //用户状态 0启用 1停用
    private Integer status;

    @Column
    private Date joinTime;

    //mapperBy 值是主表的附表对象属性
    @OneToOne(mappedBy = "userDetail")
    private UserInfo userInfo;

    public Integer getUdId() {
        return udId;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "udId=" + udId +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", joinTime=" + joinTime +
                '}';
    }

    public void setUdId(Integer udId) {
        this.udId = udId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


}
