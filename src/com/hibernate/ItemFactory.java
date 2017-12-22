package com.hibernate;

import com.pojo.Item;
import com.pojo.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ItemFactory {


    public static void main(String[] args){
        System.out.println(16 % 8);
    }

    /**
     * 查找商品的方法，可以根据关键字、类别、商品ID和用户ID进行查找
     *
     * @param key       商品名字中的关键字
     * @param type      商品类别
     * @param itemId    商品唯一识别
     * @param userId    用户唯一识别
     *
     * @return Result     返回包含商品信息的集合和分页的信息
     */
    public static Result findItem(String key, String type, int itemId, int userId, int first, int max){
        int maxPage = 0;
        int maxItem = 0;
        List<Item> items;

        Session session = HibernateFactory.getSession();
        String hql_key = "from Item where itemName like ?1";
        String hql_type = "from Item where type = ?1";
        String hql_itemId = "from Item where itemId = ?1";
        String hql_userId = "from Item where userId = ?1";

        if(key != null){
            Query<Item> q = session.createQuery(hql_key, Item.class);
            q.setParameter(1, "%"+key+"%");
            items = q.list();

        } else if(type != null){
            Query<Item> q = session.createQuery(hql_type, Item.class);
            q.setParameter(1, type);
            items = q.list();

        } else if(itemId != -1){
            Query<Item> q = session.createQuery(hql_itemId, Item.class);
            q.setParameter(1, itemId);
            items = q.list();

        } else{
            Query<Item> q = session.createQuery(hql_userId, Item.class);
            q.setParameter(1, userId);
            int size = q.list().size();
            maxPage = size / 8 == 0 ? 1 : q.list().size() % 8 == 0 ? q.list().size() / 8 : q.list().size() / 8 + 1;
            maxItem = size;

            q.setMaxResults(max);
            q.setFirstResult(first);
            items = q.list();
        }

        session.close();
        return new Result(maxPage, maxItem, items);
    }

    


}
