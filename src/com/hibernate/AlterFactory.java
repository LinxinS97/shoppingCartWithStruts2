package com.hibernate;

import com.pojo.Cart;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class AlterFactory {


    public static<E> void add(E obj){
        Session session = HibernateFactory.getSession();
        Transaction t = session.beginTransaction();

        session.save(obj);
        t.commit();

        session.close();
    }

    public static<E> void alter(E obj){
        Session session = HibernateFactory.getSession();
        Transaction t = session.beginTransaction();

        session.update(obj);
        t.commit();

        session.close();
    }

    public static<E>  void delete(E obj){
        Session session = HibernateFactory.getSession();
        Transaction t = session.beginTransaction();

        session.delete(obj);
        t.commit();

        session.close();
    }
}
