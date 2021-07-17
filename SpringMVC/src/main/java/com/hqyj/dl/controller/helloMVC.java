package com.hqyj.dl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName helloMVC
 * @description
 * @author:duanli
 * @createDate:2020/9/21 18:41
 */
@Controller
public class helloMVC {
    @RequestMapping("/hello")
    public String helloWorld(){
        System.out.println("hello World!");
        return "WEB-INF/jsp/hello.jsp";
    }

}
