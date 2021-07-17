package com.hqyj.dl.pojo;

/**
 * @ClassName Permission
 * @description
 * @author:duanli
 * @createDate:2020/9/21 22:09
 */
public class Permission {
    private int permissionId;
    private String permissionName;

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                '}';
    }
}
