package com.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CartPK implements Serializable {

    private int userId;
    private int itemId;

    @Column(name = "userId")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "itemId")
    @Id
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartPK cartPK = (CartPK) o;

        if (userId != cartPK.userId) return false;
        if (itemId != cartPK.itemId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + itemId;
        return result;
    }
}
