package com.dl;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestBean
 * @description
 * @author:duanli
 * @createDate:2020.11.2 9:12
 */
//这个注解表示将这个类注入到spring boot的容器中
@Configuration
public class RestBean {

    @Bean
    @LoadBalanced
    //这个注解是Ribbon使用负载均衡的注解
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
