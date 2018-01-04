package com.hibernate;


import com.pojo.Result;
import com.pojo.SearchKeys;
import com.pojo.UserOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class OrderFactory {
    public static void main(String[] args){
//        SearchKeys keys = new SearchKeys();
//        keys.setSearch_userId(10001);
//        keys.setSearch_name("测试");
//        BigDecimal[] price = {new BigDecimal(2.01), new BigDecimal(50.01)};
//        keys.setSearch_price(price);
//        keys.setSearch_type("服装");
//        keys.setSearch_date(new String[]{"2017-12-27 00:00:00", "2017-12-28 23:59:59"});
//        keys.setStatus(2);
//        List<List> list = getOrderWapper(keys, 0, 10).getList();
//        for(List i : list){
//            System.out.println(i.get(0));
//            System.out.println(i.get(1));
//        }
        //System.out.println(getSumOfUserOrder(10001, 2));
        System.out.println(new Date().toString());
    }

    public static int getSumOfUserOrder(int userId, int status){
        Session session = HibernateFactory.getSession();
        int sum;

        String hql_delivery = "from UserOrder where buyerId=?1 and delivery=false";
        String hql_get = "from UserOrder where buyerId=?1 and get=false and delivery=true";
        String hql_comment = "from UserOrder where buyerId=?1 and comment=false and delivery=true and get=true";
        String hql_all = "from UserOrder where buyerId=?1";
        Query<UserOrder> q = session.createQuery(hql_all, UserOrder.class);

        switch (status){
            case 1:
                q = session.createQuery(hql_delivery, UserOrder.class);
                break;
            case 2:
                q = session.createQuery(hql_get, UserOrder.class);
                break;
            case 3:
                q = session.createQuery(hql_comment, UserOrder.class);
                break;
            case 4:
                q = session.createQuery(hql_all, UserOrder.class);
                break;
        }

        q.setParameter(1, userId);
        sum = q.list().size();
        session.close();
        return sum;
    }


    public static Result<List> getOrderWapper(SearchKeys keys, int first, int max){
        Session s = HibernateFactory.getSession();
        String hql = "select new List(i, o) " +
                "from Item i left join UserOrder o on i.itemId=o.itemId where (o.buyerId=?1 or o.sellerId=?2)";

        if(keys.getSearch_name() != null)
            hql += " and i.itemName like '%" + keys.getSearch_name() + "%'";
        if(keys.getSearch_price() != null)
            hql += " and i.price between " + keys.getSearch_price()[0] + " and " + keys.getSearch_price()[1];
        if(keys.getSearch_type() != null)
            hql += " and i.type='" + keys.getSearch_type() + "'";
        if(keys.getSearch_date() != null)
            hql += " and o.startTime between '" + keys.getSearch_date()[0] + "' and '" + keys.getSearch_date()[1] + "'";
        if(keys.getStatus() != 0){
            switch (keys.getStatus()){
                case 1:
                    hql += " and o.delivery=false";
                    break;
                case 2:
                    hql += " and o.delivery=true and o.get=false";
                    break;
                case 3:
                    hql += " and o.delivery=true and o.get=true and o.comment=false";
                    break;
            }
        }
        System.out.println(hql);
        Query<List> q = s.createQuery(hql, List.class);
        q.setParameter(1, keys.getSearch_userId());
        q.setParameter(2, keys.getSearch_userId());
        int maxItem = q.list().size();
        int maxPage = maxItem / max == 0 ? 1 : maxItem % max == 0 ? maxItem / max : maxItem / max + 1;

        q.setFirstResult(first);
        q.setMaxResults(max);
        List<List> list = q.list();
        s.close();
        return new Result<>(maxPage, maxItem, list);
    }

    public static void ChangeItemStatus(BigInteger orderId, String options){
        Session s = HibernateFactory.getSession();
        Transaction t = s.beginTransaction();
        Query q = s.createQuery("update UserOrder set "+options+"=true where orderId=?1");
        q.setParameter(1, orderId);
        q.executeUpdate();
        t.commit();
        s.close();
    }
}
