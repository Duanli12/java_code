package com.dl.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyRunner
 * @description
 * @author:duanli
 * @createDate:2020.10.13 10:20
 */

/**
 * 配置类：配置默认打开自定义的浏览器
 * 启动服务打开浏览器
 */
@Configuration
public class MyRunner implements CommandLineRunner {

    @Value("${myurl}")
    private String myurl;

    @Value("${googleexcute}")
    private String googleExcutePath;

    @Value("${openurl}")
    private boolean isOpen;

    @Value("${server.port}")
    private String port;

    @Override
    public void run(String... args) throws Exception {
        if(isOpen){
            String cmd = googleExcutePath +" http://"+ myurl+":"+port;
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
                System.out.println("启动浏览器程序成功");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
