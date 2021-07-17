package com.dl.service.impl;

import com.dl.dao.RoleDao;
import com.dl.dao.RolePermissionDao;
import com.dl.pojo.Permission;
import com.dl.pojo.Role;
import com.dl.pojo.RolePermission;
import com.dl.service.RoleService;

import java.util.Set;

/**
 * @ClassName RoleServiceImpl
 * @description
 * @author:duanli
 * @createDate:2020/9/15 20:27
 */
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;
    private RolePermissionDao rolePermissionDao;

    public RoleServiceImpl(RoleDao roleDao, RolePermissionDao rolePermissionDao) {
        this.roleDao = roleDao;
        this.rolePermissionDao = rolePermissionDao;
    }

    @Override
    public Set<Role> getAll() {
        return roleDao.queryAll();
    }

    @Override
    public boolean addOne(Role role) {
        int num = roleDao.insertByRole(role);
        if(role.getPermissions()!=null){
            for (Permission permission : role.getPermissions()) {
                RolePermission rolePermission =new RolePermission();
                rolePermission.setPermissionId(permission.getPermissionId());
                rolePermission.setRoleId(role.getRoleId());
                rolePermissionDao.insert(rolePermission);
            }
        }
        return num>0;
    }

    @Override
    public boolean removeOne(Role role) {
        int num = roleDao.updateByRole(role);
        return num>0;
    }

    @Override
    public boolean modifyOne(Role role) {
        int num = roleDao.deleteByRole(role);
        return num>0;
    }

    @Override
    public boolean removeSome(Set<Integer> roleIds) {
        int num = roleDao.deleteByRoleIds(roleIds);
        return false;
    }
}
