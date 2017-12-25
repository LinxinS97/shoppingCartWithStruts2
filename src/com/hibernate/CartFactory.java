package com.hibernate;

import com.pojo.Cart;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class CartFactory {

    public static boolean IfItemExist(int userId, int itemId){
        Session s = HibernateFactory.getSession();
        Query<Cart> q = s.createQuery("from Cart where userId=?1 and itemId=?2", Cart.class);
        q.setParameter(1, userId);
        q.setParameter(2, itemId);
        boolean flag = q.list().size() > 0;
        s.close();
        return flag;
    }
}
