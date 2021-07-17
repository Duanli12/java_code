package com.fs.model;

import java.io.Serializable;

public class Page implements Serializable{
    private int itemPerPage = 5;  //每页显示5条数据
    private int allPage;  //分的页面数
    private int allItems; //全部的数据
    private int nowPage = 1;  //当前页面（默认为第一页）

    public Page() {
    }

    public Page(int itemPerPage, int allPage, int allItems, int nowPage) {
        this.itemPerPage = itemPerPage;
        this.allPage = allPage;
        this.allItems = allItems;
        this.nowPage = nowPage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "itemPerPage=" + itemPerPage +
                ", allPage=" + allPage +
                ", allItems=" + allItems +
                ", nowPage=" + nowPage +
                '}';
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public int getAllPage() {
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

    public int getAllItems() {
        return allItems;
    }

    public void setAllItems(int allItems) {
        this.allItems = allItems;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }
}
