package com.vertigo633.service;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Vertigo633 on 21.06.2015.
 */
public class HibernateUtil {

    private static SessionFactory concreteSessionFactory = null;

    public static void configure(String file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fis);

            String url = properties.getProperty("db.url");
            String login = properties.getProperty("db.login");
            String password = properties.getProperty("db.password");
            String dialect = properties.getProperty("db.dialect");

            Configuration config = new Configuration().configure("hibernate.cfg.xml")
                    .setProperty("hibernate.connection.url", url)
                    .setProperty("hibernate.connection.username", login)
                    .setProperty("hibernate.connection.password", password)
                    .setProperty("dialect", dialect);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                    applySettings(config.getProperties());
            concreteSessionFactory = config.buildSessionFactory(builder.build());

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Session getSession()
            throws HibernateException {
        return concreteSessionFactory.getCurrentSession();
    }

}