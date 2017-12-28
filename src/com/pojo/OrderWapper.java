package com.pojo;

public class OrderWapper {
    public OrderWapper(Item item, UserOrder order) {
        this.item = item;
        this.order = order;
    }

    private Item item;
    private UserOrder order;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public UserOrder getOrder() {
        return order;
    }

    public void setOrder(UserOrder order) {
        this.order = order;
    }
}
