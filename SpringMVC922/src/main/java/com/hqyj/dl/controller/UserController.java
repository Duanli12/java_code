package com.hqyj.dl.controller;

import com.github.pagehelper.PageInfo;
import com.hqyj.dl.pojo.User;
import com.hqyj.dl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName UserController
 * @description
 * @author:duanli
 * @createDate:2020/9/23 13:31
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getview")
    public String getListView(){
        //直接返回userList页面
        return "userList";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public Map<String,Object> getList(){
        List<User> userSet = userService.getAll();
        System.out.println(userSet);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "获取用户信息成功");
        result.put("userList", userSet);
        return result;

    }

    @RequestMapping("/getListByPage")
    @ResponseBody
    public Map<String,Object> getListByPage(Integer currentPage,Integer pageSize){
        PageInfo<User> pageInfo = userService.getUserByPage(currentPage,pageSize);

        System.out.println(pageInfo);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "获取用户信息成功");
        result.put("userList", pageInfo.getList());//返回查询出来的分页数据
        result.put("totalPage",pageInfo.getPages());//返回当前总共有多少页
        return result;


    }

    @RequestMapping("/getOne")
    @ResponseBody
    public Map<String,Object> getOne(Integer userId){
        Map<String,Object> result = new HashMap<>();
        User user = userService.getOneByUserId(userId);
        if(user == null){
            result.put("code", 0);
            result.put("message", "获取用户数据失败");
        }else {
            result.put("code", 0);
            result.put("message", "获取用户数据成功");
            result.put("user",user);
        }
        return result;


    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String,Object> edit(User user){
        Map<String,Object> result = new HashMap<>();
        boolean modified;
        if (user.getUserId()>0){
            modified = userService.modifyOne(user);
        }else {
           modified = userService.addOne(user);
        }

        if(modified){
            result.put("code", 0);
            result.put("message", "编辑用户信息成功");
        }else {
            result.put("code", -1);
            result.put("message", "编辑用户信息失败");

        }
        return result;


    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(User user){
        Map<String,Object> result = new HashMap<>();
        boolean deleted = userService.removeOne(user);
        if(deleted){
            result.put("code", 0);
            result.put("message", "删除用户成功");
        }else {
            result.put("code", -1);
            result.put("message", "删除用户失败");
        }
        return result;
    }

    @RequestMapping("/search")
    @ResponseBody
    public Map<String,Object> findByAddressUser(String column,String value ) {
        Map<String, Object> result = new HashMap<>();
        List<User> userList = userService.findByAddressUser(column, value);
        if (userList == null) {
            result.put("code", -1);
            result.put("massage", "搜索失败");
        } else {
            result.put("code", 0);
            result.put("userList", userList);
            result.put("massage", "搜索成功");
        }
        return result;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String username,String password){
        if (username != null && !username.equals("")&&
                password!=null && !password.equals("")){

            return userService.login(username,password);

        }
        Map<String,Object> result = new HashMap<>();

            result.put("code", -10);
            result.put("message", "用户输入异常");
            return result;

    }

    @RequestMapping("/logout")
    @ResponseBody
    public Map<String,Object> logout(){
        User user = userService.logout();
        Map<String,Object> result = new HashMap<>();

        result.put("code", 0);
        result.put("message", user.getName()+"用户已登出");
        return result;

    }


}
