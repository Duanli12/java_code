package com.dl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ServerXiaoFeiApplication
 * @description    启动类
 * @author:duanli
 * @createDate:2020.11.2 9:09
 */

@EnableDiscoveryClient
@SpringBootApplication
public  class ServerXiaoFeiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerXiaoFeiApplication.class, args);
    }
}
