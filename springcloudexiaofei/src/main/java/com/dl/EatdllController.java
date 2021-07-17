package com.dl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @ClassName EatdllController
 * @description
 * @author:duanli
 * @createDate:2020.11.2 9:13
 */
@RestController
public class EatdllController {
    //注入
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/eat")
    public HashMap<String,Object> eat(){
        HashMap<String,Object> data = new HashMap<String,Object>();
        data = restTemplate.getForObject("http://dll/getDL",HashMap.class);
        return data;
    }
}
