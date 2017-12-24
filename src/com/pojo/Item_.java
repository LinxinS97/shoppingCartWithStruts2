package com.pojo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Item.class)
public class Item_{
    public static volatile SingularAttribute<Item, Integer> itemId;
    public static volatile SingularAttribute<Item, Integer> userId;
    public static volatile SingularAttribute<Item, Integer> stock;
    public static volatile SingularAttribute<Item, String> itemName;
    public static volatile SingularAttribute<Item, String> type;
    public static volatile SingularAttribute<Item, String> itemImg;
    public static volatile SingularAttribute<Item, BigDecimal> price;
    public static volatile SingularAttribute<Item, Integer> completeOrder;

}
