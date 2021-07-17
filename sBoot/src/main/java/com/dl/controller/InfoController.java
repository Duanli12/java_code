package com.dl.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName InfoController
 * @description
 * @author:duanli
 * @createDate:2020.10.13 23:06
 */
public class InfoController {
    @RequestMapping(value = {"","/login"})
    public String login(){
        return "login";
    }
}
