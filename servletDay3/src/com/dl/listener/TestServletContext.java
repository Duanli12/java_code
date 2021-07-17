package com.dl.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/1
 */
@WebListener
public class TestServletContext implements ServletContextListener {
    /*监听servletcontext的初始化*/
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("servletcontext被初始化了");
    }
    /*监听servletcontext的销毁*/
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("servletcontext被销毁了");
    }
}
