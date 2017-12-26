package com.pojo;

public class CartItem {

    public CartItem(Item item, int num, String disable) {
        this.item = item;
        this.num = num;
        this.disable = disable;
    }

    public CartItem(Item item, int num, String disable, int itemId) {
        this.item = item;
        this.num = num;
        this.disable = disable;
        this.itemId = itemId;
    }

    private Item item;
    private int num;
    private String disable;
    private int itemId;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
