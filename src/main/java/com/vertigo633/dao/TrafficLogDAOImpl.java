package com.vertigo633.dao;

import com.vertigo633.entities.TrafficLog;
import com.vertigo633.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Vertigo633 on 21.06.2015.
 */
public class TrafficLogDAOImpl implements TrafficLogDAO {

    public void saveLog(TrafficLog log) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(log);
        tx.commit();
    }
}
