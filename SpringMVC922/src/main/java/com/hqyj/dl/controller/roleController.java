package com.hqyj.dl.controller;

import com.hqyj.dl.pojo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName roleController
 * @description
 * @author:duanli
 * @createDate:2020/9/21 22:10
 */
@Controller
@RequestMapping("/role")
public class roleController {
    @GetMapping("/registerTest")
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
        roleResult.put("message","成功注册了小老弟");
        return roleResult;

    }

}
