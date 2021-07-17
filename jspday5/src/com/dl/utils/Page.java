package com.dl.utils;

/**
 * @description 分页信息
 * @author:duanli
 * @createDate:2020/9/9 18:46
 */
public class Page {
    //每页显示多少条记录
    private int pageNum = 5;
    //需要进行分页的所有记录数
    private int totalNum = 0;
    //需要分多少页
    private int pageTotal;
    //显示哪一页的记录
    private int nowPage = 1;

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", totalNum=" + totalNum +
                ", pageTotal=" + pageTotal +
                ", nowPage=" + nowPage +
                '}';
    }

    public Page() {
    }

    public Page(int pageNum, int totalNum, int pageTotal, int nowPage) {
        this.pageNum = pageNum;
        this.totalNum = totalNum;
        this.pageTotal = pageTotal;
        this.nowPage = nowPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getPageTotal(int i) {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }
}
