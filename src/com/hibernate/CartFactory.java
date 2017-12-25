package com.hibernate;

import com.pojo.Cart;
import com.pojo.Result;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


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

    public static Result GetUserCart(int userId, int first, int max){

        Session s = HibernateFactory.getSession();
        Query<Cart> q = s.createQuery("from Cart where userId=?1", Cart.class);
        q.setParameter(1, userId);
        int maxItem = q.list().size();
        int maxPage = maxItem / max == 0 ? 1 : maxItem % max == 0 ? maxItem / max : maxItem / max + 1;

        q.setFirstResult(first);
        q.setMaxResults(max);
        List<Cart> cartList = q.list();
        s.close();
        return new Result<>(maxPage, maxItem, cartList);
    }
}
