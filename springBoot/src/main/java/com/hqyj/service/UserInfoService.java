package com.hqyj.service;

import com.hqyj.pojo.RoleInfo;
import com.hqyj.pojo.UserInfo;
import com.hqyj.pojo.UserInfoParam;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public interface UserInfoService  {

    HashMap<String,Object> login(UserInfo userInfo, HttpServletRequest request);

    /**
     * 查询所有
     * @return
     */
    Page<UserInfo> select(UserInfoParam userInfoParam);

    /**
     * 修改
     * @param uId
     * @return
     */
    boolean update(Integer uId);

    /**
     * 查询所有角色
     * @return
     */
    List<RoleInfo> selectRole();

    /**
     * 新增
     * @param userInfoParam
     * @return
     */
    boolean add(UserInfoParam userInfoParam);

    /**
     * 修改
     * @param userInfoParam
     * @return
     */
    boolean update(UserInfoParam userInfoParam);

    /**
     * 根据用户uId查询用户对象
     * @param uId
     * @return
     */
    UserInfo selectByUId(Integer uId);

    /**
     * 根据用户名
     * @param name
     * @return
     */
    UserInfo selectByName(String name);


    boolean del(Integer uId);

    boolean delAll(String uIds,String udIds);

    void excelWrite(HttpServletResponse response);

    /**
     * 邮件发送
     * @param email 收件人邮箱
     * @return true发送成功,false发送失败
     */
    boolean sendEmail(String email,HttpServletRequest request);

    HashMap<String, Object> loginShiro(UserInfo userInfo, HttpServletRequest request);

    /*void loginShiro(UserInfo userInfo, HttpServletRequest request );*/

}
