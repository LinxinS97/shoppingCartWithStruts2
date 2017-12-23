package com.pojo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserComment.class)
public class UserComment_ {
    public static volatile SingularAttribute<UserComment, Integer> userId;
    public static volatile SingularAttribute<UserComment, Integer> itemId;
    public static volatile SingularAttribute<UserComment, Integer> apprise;
    public static volatile SingularAttribute<UserComment, String> comment;
}
