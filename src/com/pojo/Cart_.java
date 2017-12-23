package com.pojo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Cart.class)
public class Cart_ {
    public static volatile SingularAttribute<Cart, Integer> userId;
    public static volatile SingularAttribute<Cart, Integer> itemId;
    public static volatile SingularAttribute<Cart, Integer> num;
    public static volatile SingularAttribute<Cart, String> disable;
}
