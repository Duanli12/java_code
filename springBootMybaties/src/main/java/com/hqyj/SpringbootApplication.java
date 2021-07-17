package com.hqyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName SpringbootApplication
 * @description
 * @author:duanli
 * @createDate:2020.10.22 10:07
 */

@SpringBootApplication
@MapperScan("com.hqyj.dao") //扫描数据层接口
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class,args);
    }
}
