package com.hqyj.realm;


import com.hqyj.pojo.UserInfo;
import com.hqyj.service.UserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName realm
 * @description
 * @author:duanli
 * @createDate:2020.10.22 11:12
 */
public class realm extends AuthorizingRealm {

    //注入service
    @Autowired
    UserInfoService userInfoService;

    @Override //授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1.拿到认证通过的用户名
        String userName = principals.fromRealm(this.getName()).iterator().next() + "";
        //2.查询用户拥有的权限或角色
        //创建权限
        List<String> listPower = new ArrayList<>();
       /* listPower.add("userInforList");
        listPower.add("userInforAdd");
        listPower.add("userlist");
        listPower.add("userdel");*/

        //创建角色
        List<String> listRole = new ArrayList<>();
     /*   listRole.add("admin");
        listRole.add("vip");*/
        //dl 用户可以访问会员的所有页面 dj用户只可以访问会员列表页面

        if (userName.equals("dl")) {
            //添加会员列表访问权限
            listPower.add("userlist");
            //添加会员管理访问权限
            listPower.add("usermag");
            //添加会员删除访问权限
            listPower.add("vip");


        } else if (userName.equals("dj")) {
            //添加会员列表访问权限
            listPower.add("userlist");

        }

        //3.把权限和角色添加到shiro的授权对象里
        SimpleAuthorizationInfo au = new SimpleAuthorizationInfo();
        //添加权限集合
        au.addStringPermissions(listPower);
        //添加角色集合
        au.addRoles(listRole);
        return au;
    }


    @Override //认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       //拿到用户名
        UsernamePasswordToken upt =(UsernamePasswordToken) token;
        String userName = upt.getPrincipal()+"";
        //从数据库中取出用户的加密密码
        UserInfo userInfo = userInfoService.selectByName(userName);
        //创建密码校验的凭证对象
        AuthenticationInfo auto = new SimpleAuthenticationInfo(userInfo.getUserName(),userInfo.getUserPwd(),new Md5Hash(userName),this.getName());

        return auto;
    }
}
