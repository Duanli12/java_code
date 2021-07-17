package com.dl.service;

import com.dl.pojo.Role;


import java.util.Set;

/**
 * @ClassName RoleService
 * @description
 * @author:duanli
 * @createDate:2020/9/15 20:27
 */
public interface RoleService {
    Set<Role> getAll();
    boolean addOne(Role role);
    boolean removeOne(Role role);
    boolean modifyOne(Role role);
    boolean removeSome(Set<Integer> roleIds);
}
