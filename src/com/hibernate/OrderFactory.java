package com.hibernate;


import com.pojo.UserOrder;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class OrderFactory {
    public static void main(String[] args){
//        System.out.println(getSumofUserOrder(10001));
    }

    public static int getSumofUserOrder(int userId){
        Session session = HibernateFactory.getSession();
        Query<UserOrder> q = session.createQuery("from UserOrder where userId=?1 and delivery=true", UserOrder.class);
        q.setParameter(1, userId);
        int sum = q.list().size();
        session.close();
        return sum;
    }
}
