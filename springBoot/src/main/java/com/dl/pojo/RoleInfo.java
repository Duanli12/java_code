package com.dl.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RoleInfo
 * @description
 * @author:duanli
 * @createDate:2020.10.15 8:49
 */
@Table
@Entity
public class RoleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rId;
    @Column
    private String rName;
    @Column
    private Date joinTime;
    @Column
    private String remark;

    @ManyToMany(mappedBy = "roleInfoList")
    private List<UserInfo> userInfoList;

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "rId=" + rId +
                ", rName='" + rName + '\'' +
                ", joinTime=" + joinTime +
                ", remark='" + remark + '\'' +
                ", userInfoList=" + userInfoList +
                '}';
    }

    public RoleInfo() {
    }


    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
