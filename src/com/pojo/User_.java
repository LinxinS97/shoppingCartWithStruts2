package com.pojo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, String> teleNum;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> userImg;
}
