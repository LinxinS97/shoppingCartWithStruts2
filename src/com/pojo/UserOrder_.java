package com.pojo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.sql.Timestamp;

@StaticMetamodel(UserOrder.class)
public class UserOrder_ {
    public static volatile SingularAttribute<UserOrder, BigDecimal> orderId;
    public static volatile SingularAttribute<UserOrder, Integer> itemId;
    public static volatile SingularAttribute<UserOrder, Integer> userId;
    public static volatile SingularAttribute<UserOrder, Timestamp> startTime;
    public static volatile SingularAttribute<UserOrder, Timestamp> endTime;
    public static volatile SingularAttribute<UserOrder, String> isDelivery;
    public static volatile SingularAttribute<UserOrder, String> isGet;
    public static volatile SingularAttribute<UserOrder, String> isComment;
}
