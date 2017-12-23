package com.pojo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@StaticMetamodel(Order.class)
public class Order_ {
    public static volatile SingularAttribute<Order, Integer> itemId;
    public static volatile SingularAttribute<Order, Integer> userId;
    public static volatile SingularAttribute<Order, Timestamp> startTime;
    public static volatile SingularAttribute<Order, Timestamp> endTime;
    public static volatile SingularAttribute<Order, String> isPay;
    public static volatile SingularAttribute<Order, String> isDelivery;
    public static volatile SingularAttribute<Order, String> isGet;
    public static volatile SingularAttribute<Order, String> isComment;
}
