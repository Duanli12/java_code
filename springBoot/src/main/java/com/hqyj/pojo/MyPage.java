package com.hqyj.pojo;

/**
 * 分页对象
 */
public class MyPage {
    //页码，默认第一页
    private  int page=1;
    //每页显示的行数，默认2行
    private  int row=2;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }



}
