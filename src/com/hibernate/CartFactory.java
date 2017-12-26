package com.hibernate;

import com.pojo.Cart;
import com.pojo.CartItem;
import com.pojo.Item;
import com.pojo.Result;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class CartFactory {
//    public static void main(String[] args){
//        CartFactory.ChangeItemDisabled(13);
//    }

    public static boolean IfItemExist(int userId, int itemId){
        Session s = HibernateFactory.getSession();
        Query<Cart> q = s.createQuery("from Cart where userId=?1 and itemId=?2", Cart.class);
        q.setParameter(1, userId);
        q.setParameter(2, itemId);
        boolean flag = q.list().size() > 0;
        s.close();
        return flag;
    }

    public static Result<Item> GetUserCart(int userId, int first, int max){

        Session s = HibernateFactory.getSession();
        Query<Item> q = s.createQuery("select i " +
                "from Cart c left join Item i on i.itemId = c.itemId where c.userId=?1", Item.class);
        q.setParameter(1, userId);
        int maxItem = q.list().size();
        int maxPage = maxItem / max == 0 ? 1 : maxItem % max == 0 ? maxItem / max : maxItem / max + 1;

        q.setFirstResult(first);
        q.setMaxResults(max);
        List<Item> cartList = q.list();
        s.close();
        return new Result<>(maxPage, maxItem, cartList);
    }

    public static Result<List> GetUserCartList(int userId, int first, int max){

        Session s = HibernateFactory.getSession();
        Query q = s.createQuery("select new list(i, c.num, c.disable, c.itemId) " +
                "from Cart c left join Item i on i.itemId = c.itemId where c.userId=?1");
        q.setParameter(1, userId);
        int maxItem = q.list().size();
        int maxPage = maxItem / max == 0 ? 1 : maxItem % max == 0 ? maxItem / max : maxItem / max + 1;

        q.setFirstResult(first);
        q.setMaxResults(max);
        List<List> cartList = q.list();
        s.close();
        return new Result<>(maxPage, maxItem, cartList);
    }

    public static void ChangeItemDisabled(int itemId){
        Session s = HibernateFactory.getSession();
        Transaction t = s.beginTransaction();
        Query q = s.createQuery("update Cart set disable='1' where itemId=?1");
        q.setParameter(1, itemId);
        q.executeUpdate();
        t.commit();
        s.close();
    }
}
