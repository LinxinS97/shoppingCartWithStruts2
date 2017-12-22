package com.pojo;

import java.util.List;

public class Result {
    private int maxPage;
    private int maxItem;
    private List<Item> list;

    public Result(int maxPage, int maxItem, List<Item> list) {
        this.maxPage = maxPage;
        this.maxItem = maxItem;
        this.list = list;
    }

    public Result(int maxPage, List<Item> list) {
        this.maxPage = maxPage;
        this.list = list;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public int getMaxItem() {
        return maxItem;
    }

    public void setMaxItem(int maxItem) {
        this.maxItem = maxItem;
    }
}
