package com.pojo;

import javax.persistence.*;

@Entity
@IdClass(CartPK.class)
public class Cart {

    private int userId;
    private int itemId;
    private int num;
    private String disable;

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "itemId")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (userId != cart.userId) return false;
        if (itemId != cart.itemId) return false;
        if (num != cart.num) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + itemId;
        result = 31 * result + num;
        return result;
    }

    @Basic
    @Column(name = "disable", insertable = false)
    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }
}
