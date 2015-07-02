package com.vertigo633.dao;

import com.vertigo633.entities.Subscriber;
import com.vertigo633.service.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Vertigo633 on 22.06.2015.
 */
public class SubscriberDAOImpl implements SubscriberDAO {

    public void save(Subscriber subscriber) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(subscriber);
        System.out.println("Subscriber created");
        tx.commit();
    }

    public Subscriber getSubscriber(String subscriberName) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Criteria c = session.createCriteria(Subscriber.class).add
                (Restrictions.eq("name", subscriberName));
        Subscriber s = (Subscriber) c.uniqueResult();
        tx.commit();
        return s;
    }

    public List<Subscriber> getSubscribers() {
        return null;
    }

    public void update(Subscriber subscriber) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.update(subscriber);
        tx.commit();
    }
}

