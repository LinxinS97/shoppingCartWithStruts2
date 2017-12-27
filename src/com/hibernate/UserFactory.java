package com.hibernate;

import com.pojo.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;

public class UserFactory {



    /**
     * Search user's info according to his or her userId.
     *
     * @param userId User's primary key.
     * @return User A pojo class which contented user's info.
     */
    public static User findUser(int userId, String telNum){
        Session session = HibernateFactory.getSession();
        String hql = "from User where teleNum = ?1";
        Query<User> q = null;
        User user = null;

        if(userId != -1) {
            hql = "from User where userId = ?1";
            q = session.createQuery(hql, User.class);
            q.setParameter(1, userId);
        } else{
            q = session.createQuery(hql, User.class);
            q.setParameter(1, telNum);
        }

        if(q.list().size() > 0) {
            user = q.list().get(0);
            System.out.println(user.getTeleNum());
        }
        session.close();
        return user;
    }


}
