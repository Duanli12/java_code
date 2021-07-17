package com.hqyj.dl.pojo;

/**
 * @ClassName Role
 * @description
 * @author:duanli
 * @createDate:2020/9/21 22:08
 */
public class Role {
    private int roleId;
    private String roleName;
    private Permission permission;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", permission=" + permission +
                '}';
    }
}
