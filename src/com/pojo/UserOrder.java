package com.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
public class UserOrder {
    private BigInteger orderId;
    private int itemId;
    private int userId;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean isDelivery;
    private boolean isGet;
    private boolean isComment;


    @Id
    @Column(name = "orderId")
    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    @Column(name = "itemId")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "startTime")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "isDelivery")
    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    @Basic
    @Column(name = "isGet")
    public boolean isGet() {
        return isGet;
    }

    public void setGet(boolean get) {
        isGet = get;
    }

    @Basic
    @Column(name = "isComment")
    public boolean isComment() {
        return isComment;
    }

    public void setComment(boolean comment) {
        isComment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOrder userOrder = (UserOrder) o;

        if (itemId != userOrder.itemId) return false;
        if (userId != userOrder.userId) return false;
        if (isDelivery != userOrder.isDelivery) return false;
        if (isGet != userOrder.isGet) return false;
        if (isComment != userOrder.isComment) return false;
        if (startTime != null ? !startTime.equals(userOrder.startTime) : userOrder.startTime != null) return false;
        if (endTime != null ? !endTime.equals(userOrder.endTime) : userOrder.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + userId;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (isDelivery ? 1 : 0);
        result = 31 * result + (isGet ? 1 : 0);
        result = 31 * result + (isComment ? 1 : 0);
        return result;
    }

}
