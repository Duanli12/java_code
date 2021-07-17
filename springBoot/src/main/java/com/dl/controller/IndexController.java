package com.dl.controller;

import com.dl.pojo.UserInfo;
import com.dl.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @ClassName IndexController
 * @description
 * @author:duanli
 * @createDate:2020.10.13 9:35
 */
@Controller
public class IndexController {
    @Autowired
    private UserInfoService userInfoService;
    //访问login页面可以是http://localhost:8080/或者http://localhost:8080/login
    @RequestMapping(value = {"","/login"})
    public String login(){
        return "login";
    }

    //访问系统主页面
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //登录
    @RequestMapping("/loginSubmit")
    @ResponseBody
    public HashMap<String, Object> loginSubmit(UserInfo userInfo, HttpServletRequest request){
        return userInfoService.login(userInfo,request);
    }

}
