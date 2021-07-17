package com.dl.pojo;

import java.util.Set;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/15 14:07
 */
public class Role {
    private int roleId;//角色id
    private String name;//角色名称
    private int state;//状态
    private Permission permission;//对应相应的Permission
    private Set<Permission> permissions;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", permission=" + permission +
                ", permissions=" + permissions +
                '}';
    }
}
