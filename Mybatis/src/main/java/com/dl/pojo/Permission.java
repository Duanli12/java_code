package com.dl.pojo;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/15 14:07
 */
public class Permission {
    private int permissionId;//权限id
    private String name;//权限名称
    private int parentPid;//父亲的权限id
    private String type;//权限类别：menu菜单，button按钮，page页面
    private String url;//权限对应的URL
    private int sort;//用于（menu）排序使用的索引号
    private int state;//权限的状态，0不可用

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentPid() {
        return parentPid;
    }

    public void setParentPid(int parentPid) {
        this.parentPid = parentPid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId='" + permissionId + '\'' +
                ", name='" + name + '\'' +
                ", parentPid=" + parentPid +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", sort=" + sort +
                ", state=" + state +
                '}';
    }
}
