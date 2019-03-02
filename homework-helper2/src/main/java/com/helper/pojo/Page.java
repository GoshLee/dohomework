package com.helper.pojo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @param <E>
 */
public class Page<E> implements Serializable {
    /**
     * 当前页面
     */
    private int currentPage = 1;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 总数量
     */
    private long totalNumber;
    /**
     * 数据集
     */
    private List<E> list;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public Page() {
    }

    public Page(int currentPage, long totalPage, long totalNumber, List<E> list) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalNumber = totalNumber;
        this.list = list;
    }
}
