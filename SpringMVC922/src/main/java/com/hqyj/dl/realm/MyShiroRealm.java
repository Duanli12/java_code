package com.hqyj.dl.realm;

import com.hqyj.dl.pojo.Permission;
import com.hqyj.dl.pojo.Role;
import com.hqyj.dl.pojo.User;
import com.hqyj.dl.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName MyShiroRealm
 * @description
 * @author:duanli
 * @createDate:2020/9/25 9:17
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //有人来认证，传递过来token，包含用户基本信息（账号名）
    //要返回一个系统里面相关用户的认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //拿到要认证的用户的用户账号
        String username = (String) authenticationToken.getPrincipal();
        //根据要认证的用户账号去系统查询相关用户信息
        User user = userService.getOneByUsername(username);
        if (user == null) {
            throw new UnknownAccountException(username + ",查无此人");
        }
        //获取当前用户的会话域
        Session session = SecurityUtils.getSubject().getSession();
        //在会话域中保存这个用户，以备后用
        session.setAttribute("user", user);

        String password = user.getPassword();
        String realmName = this.getName();
        ByteSource byteSource=ByteSource.Util.bytes(user.getUsername());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password,byteSource, realmName);

        return info;
    }

    //传入的参数就是待授权的用户
    //告诉shiro系统，某个用户对应的权限相关信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //拿到待授权的用户账号
        String username =(String) principalCollection.getPrimaryPrincipal();
        //根据待授权用户账号去系统查询相关用户授权信息
        User user = userService.getOneByUsername(username);
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        //收集user的角色信息
        for(Role role:user.getRoles()){
            roles.add(role.getName());

            if (role.getPermissions()!=null){
                for (Permission permission:role.getPermissions()){
                    permissions.add(permission.getPermissionName());
                }

            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }


}
