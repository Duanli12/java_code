package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

/**
 * 视图
 *
 * @author 
 * @version V1.0
 * @date 
 */
@Api(tags = "视图")
@Controller
@RequestMapping("/index")
public class IndexController {
	
    @GetMapping("/login")
    public String logout() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("/500")
    public String error405() {
        return "error/500";
    }

    @GetMapping("/main")
    public String indexHome() {
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/build")
    public String build() {
        return "build";
    }
    @GetMapping("/clazz")
    public String sysContent() {
        return "syscontent/clazz";
    }
    @GetMapping("/xinxi")
    public String xinxi() {
        return "syscontent/xinxi";
    }
    @GetMapping("/kecheng")
    public String kecheng() {
        return "syscontent/kecheng";
    }
    @GetMapping("/jianyi")
    public String jianyi() {
    	return "syscontent/jianyi";
    }
    @GetMapping("/fenlei")
    public String fenlei() {
    	return "syscontent/fenlei";
    }
    
 
}
