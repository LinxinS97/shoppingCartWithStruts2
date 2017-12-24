package com.pojo;

import java.math.BigDecimal;
import java.util.Arrays;

public class SearchKeys {
    private int search_itemId;
    private int search_userId;
    private String search_name;
    private BigDecimal[] search_price;
    private String search_type;
    private int[] search_order;
    private int[] search_stock;

    public SearchKeys(String search_name, BigDecimal[] search_price,
                      String search_type, int[] search_order, int[] search_stock) {
        this.search_name = search_name;
        this.search_price = search_price;
        this.search_type = search_type;
        this.search_order = search_order;
        this.search_stock = search_stock;
    }

    public SearchKeys(){

    }

    public String getSearch_name() {
        return search_name;
    }

    public void setSearch_name(String search_name) {
        this.search_name = search_name;
    }

    public BigDecimal[] getSearch_price() {
        return search_price;
    }

    public void setSearch_price(BigDecimal[] search_price) {
        this.search_price = search_price;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public int[] getSearch_order() {
        return search_order;
    }

    public void setSearch_order(int[] search_order) {
        this.search_order = search_order;
    }

    public int[] getSearch_stock() {
        return search_stock;
    }

    public void setSearch_stock(int[] search_stock) {
        this.search_stock = search_stock;
    }

    public int getSearch_userId() {
        return search_userId;
    }

    public void setSearch_userId(int search_userId) {
        this.search_userId = search_userId;
    }

    public int getSearch_itemId() {
        return search_itemId;
    }

    public void setSearch_itemId(int search_itemId) {
        this.search_itemId = search_itemId;
    }

    @Override
    public String toString() {
        return "SearchKeys{" +
                "search_itemId=" + search_itemId +
                ", search_userId=" + search_userId +
                ", search_name='" + search_name + '\'' +
                ", search_price=" + Arrays.toString(search_price) +
                ", search_type='" + search_type + '\'' +
                ", search_order=" + Arrays.toString(search_order) +
                ", search_stock=" + Arrays.toString(search_stock) +
                '}';
    }
}
