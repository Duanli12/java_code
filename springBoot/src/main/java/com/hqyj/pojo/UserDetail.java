package com.hqyj.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer udId;

    @Column(length = 20)
    private String tel;

    @Column(length = 20)
    private String email;

    //头像路径
    @Column(length = 20)
    private String url;

    // 用户使用状态  1启用  0 停用
    @Column(length = 20)
    private Integer status;
    //加入时间
    @Column
    private Date joinTime;
    //mappedBy 值是  主表的 附表对象属性
    @OneToOne(mappedBy = "userDetail")
    private  UserInfo userInfo;


    public Integer getUdId() {
        return udId;
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
