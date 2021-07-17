package com.hqyj.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.hqyj.realm.realm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @ClassName ShiroConfig
 * @description
 * @author:duanli
 * @createDate:2020.10.22 11:26
 */
@Configuration
public class ShiroConfig {
    //配置密码匹配器
    @Bean(name={"hashedCred"})
    public HashedCredentialsMatcher getMatcher(){
        HashedCredentialsMatcher hsc = new HashedCredentialsMatcher();
        //加密算法
        hsc.setHashAlgorithmName("MD5");
        //加密次数
        hsc.setHashIterations(1024);
        return hsc;
    }
    /**
     * 配置自定义的 realm
     * @param hs
     * @return
     */
    @Bean(name={"myRealm"})
    public realm getRealm(@Qualifier("hashedCred") HashedCredentialsMatcher hs ){
        realm realm = new realm();
        realm.setCredentialsMatcher(hs);
        return realm;
    }
    /**
     * 配置安全管理类
     * @return
     */
    @Bean(name={"securityManager"})
    public DefaultWebSecurityManager getSecurity(@Qualifier("myRealm") realm realm){
        DefaultWebSecurityManager dm = new DefaultWebSecurityManager();
        dm.setRealm(realm);
        return dm;
    }

    @Bean
    public LifecycleBeanPostProcessor getProcess(){
        return new LifecycleBeanPostProcessor();
    }

    //html 标签配置
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
    /**
     * 配置权限
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager sm){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(sm);
        //配置登录页面
        bean.setLoginUrl("/login");
        //配置权限页面
        bean.setUnauthorizedUrl("/noPower");
        //配置权限的map
        HashMap<String, String> map = new HashMap<String, String>();

        //必须登录才能访问
        map.put("/index", "authc");
       /* //设置访问用户管理业页面需要userInforList权限
        map.put("/userInfo/list", "perms[userInforList]");*/
        //设置访问会员列表业页面需要userList权限
     /*   map.put("/userInfo/userlist", "perms[userlist]");
        //配置用户拥有VIP角色的可以访问会员删除页面
        map.put("/userInfo/userdel", "roles[vip]");
        //设置用户不可以访问会员管理页面
        map.put("/userInfo/usermag", "perms[usermag]");*/
        /*//设置访问用户管理新增页面需要userInforAdd权限
        map.put("/userInfo/add", "perms[userInforAdd]");
        ////设置访问用户管理修改页面需要admin角色
        map.put("/userInfo/Update", "roles[admin]");*/
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}
