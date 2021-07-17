package com.dl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName dlController
 * @description
 * @author:duanli
 * @createDate:2020.10.30 10:27
 */
@RestController
public class dlController {
    @RequestMapping(value = {"","/getDL"})
    public Map<String,Object>  getUser(){
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("name","dll");
        data.put("foodName","汉堡A");
        return data;
    }
}
