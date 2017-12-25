package com.pojo;

import java.util.List;

public class Result<E> {
    private int maxPage;
    private int maxItem;
    private List<E> list;

    public Result(int maxPage, int maxItem, List<E> list) {
        this.maxPage = maxPage;
        this.maxItem = maxItem;
        this.list = list;
    }

    public Result(int maxPage, List<E> list) {
        this.maxPage = maxPage;
        this.list = list;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getMaxItem() {
        return maxItem;
    }

    public void setMaxItem(int maxItem) {
        this.maxItem = maxItem;
    }
}
