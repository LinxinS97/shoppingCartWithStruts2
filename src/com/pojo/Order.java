package com.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(OrderPK.class)
public class Order {
    private int itemId;
    private int userId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String isPay;
    private String isDelivery;
    private String isGet;
    private String isComment;

    @Id
    @Column(name = "itemId")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Id
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
    @Column(name = "isPay", insertable = false)
    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    @Basic
    @Column(name = "isDelivery", insertable = false)
    public String getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(String isDelivery) {
        this.isDelivery = isDelivery;
    }

    @Basic
    @Column(name = "isGet", insertable = false)
    public String getIsGet() {
        return isGet;
    }

    public void setIsGet(String isGet) {
        this.isGet = isGet;
    }

    @Basic
    @Column(name = "isComment", insertable = false)
    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (itemId != order.itemId) return false;
        if (userId != order.userId) return false;
        if (startTime != null ? !startTime.equals(order.startTime) : order.startTime != null) return false;
        if (endTime != null ? !endTime.equals(order.endTime) : order.endTime != null) return false;
        if (isPay != null ? !isPay.equals(order.isPay) : order.isPay != null) return false;
        if (isDelivery != null ? !isDelivery.equals(order.isDelivery) : order.isDelivery != null) return false;
        if (isGet != null ? !isGet.equals(order.isGet) : order.isGet != null) return false;
        if (isComment != null ? !isComment.equals(order.isComment) : order.isComment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + userId;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (isPay != null ? isPay.hashCode() : 0);
        result = 31 * result + (isDelivery != null ? isDelivery.hashCode() : 0);
        result = 31 * result + (isGet != null ? isGet.hashCode() : 0);
        result = 31 * result + (isComment != null ? isComment.hashCode() : 0);
        return result;
    }
}
