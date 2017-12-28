package com.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CommentFactory {

    public static List<List> getItemComment(int itemId){
        Session s = HibernateFactory.getSession();
        Query<List> q = s.createQuery("select new List(u, c) " +
                "from User u left join UserComment c on u.userId = c.userId " +
                "where c.itemId=?1", List.class);
        q.setParameter(1, itemId);
        List<List> list = q.list();
        s.close();
        return list;
    }
}
