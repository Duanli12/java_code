package com.hqyj.pojo;

import java.util.Date;

//用户参数实体
public class UserInfoParam  extends  MyPage{
        private Integer uId;
        private String userName;
        private String userPwd;
        private String tel;
        private String email;
        //头像路径
        private String url;
        private Date joinTime;
        //是否启用
        private Integer status;
        private String roleId;

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

        public Date getJoinTime() {
                return joinTime;
        }

        public void setJoinTime(Date joinTime) {
                this.joinTime = joinTime;
        }

        public Integer getStatus() {
                return status;
        }

        public void setStatus(Integer status) {
                this.status = status;
        }

        public String getRoleId() {
                return roleId;
        }

        public void setRoleId(String roleId) {
                this.roleId = roleId;
        }
}
