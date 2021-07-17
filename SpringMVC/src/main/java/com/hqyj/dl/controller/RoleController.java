package com.hqyj.dl.controller;


import com.hqyj.dl.pojo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController{
    @RequestMapping("/register")
    public String register(){
        return "role";
    }
    @RequestMapping("/toRegister")
    @ResponseBody
    public Map<String,Object> toRegister(@RequestBody Role role){
        System.out.println(role);
        Map<String,Object> roleResult = new HashMap<>();
        roleResult.put("code",0);
        roleResult.put("role",role);
        roleResult.put("message","成功注册");
        return roleResult;

    }

}
