package com.dl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName SpringCloudServer
 * @description
 * @author:duanli
 * @createDate:2020.10.30 10:16
 */

@EnableDiscoveryClient //发现服务注册中心的注解
@SpringBootApplication
public class SpringCloudServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServer.class, args);
    }
}
