package com.hqyj.controller;

import com.hqyj.pojo.RoleInfo;
import com.hqyj.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName RoleInfoController
 * @description
 * @author:duanli
 * @createDate:2020.10.23 14:21
 */
@Controller
@RequestMapping("roleInfo")
public class RoleInfoController {
    @Autowired
    RoleInfoService roleInfoService;
    @RequestMapping("/list")
    @ResponseBody
    public List<RoleInfo> list(){
        return roleInfoService.select();
    }
}
