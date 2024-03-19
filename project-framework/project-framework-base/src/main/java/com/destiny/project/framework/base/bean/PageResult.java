package com.destiny.project.framework.base.bean;

import java.util.Collections;
import java.util.List;

public class PageResult<T> {
    private List<T> list;
    private int pageNum;
    private int pageSize;
    private Long total;
    private int totalPage;

    public PageResult() {
        this.list = Collections.EMPTY_LIST;
        this.pageNum = 1;
        this.pageSize = 10;
        this.total = 0L;
        this.totalPage = 0;
    }

    public PageResult(int currentPage, int pageSize, long total, List<T> list) {
        this.list = Collections.EMPTY_LIST;
        this.pageNum = 1;
        this.pageSize = 10;
        this.total = 0L;
        this.totalPage = 0;
        this.pageNum = currentPage;
        this.pageSize = pageSize;
        this.totalPage = (int) (total + (long) pageSize - 1L) / pageSize;
        this.total = total;
        this.list = list;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
