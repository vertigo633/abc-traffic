package com.vertigo633.main;

import com.vertigo633.dao.SubscriberDAO;
import com.vertigo633.dao.SubscriberDAOImpl;
import com.vertigo633.entities.Subscriber;
import com.vertigo633.service.HibernateUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.Semaphore;

/**
 * Created by Vertigo633 on 18.06.2015.
 */
public class Main {
    static final String SETTINGS_FILE = "src/main/resources/config.properties";
    static final int MAX_DB_CONNECTIONS = 130;

    public static void main(String[] args) {

        SubscriberDAO subscriberDAO = new SubscriberDAOImpl();
        FileInputStream fis = null;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(SETTINGS_FILE);
            property.load(fis);

            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String start_date_asText = property.getProperty("start_date");
            String end_date_asText = property.getProperty("end_date");
            Date start_date = dateFormatter.parse(start_date_asText);
            Date end_date = dateFormatter.parse(end_date_asText);

            Semaphore semaphore = new Semaphore(MAX_DB_CONNECTIONS);
            String unique_subscribers_asText = property.getProperty("unique_subscribers");
            int unique_subscribers = Integer.parseInt(unique_subscribers_asText);
            HibernateUtil.configure(SETTINGS_FILE);

            for (int i = 0; i < unique_subscribers; i++) {
                String name = "User" + i;
                Timer timer = new Timer();
                Subscriber subscriber = subscriberDAO.getSubscriber(name);
                if (subscriber == null) {
                    subscriber = new Subscriber(name);
                    subscriberDAO.save(subscriber);
                }
                timer.schedule(new TrafficTask(subscriber, end_date, semaphore), start_date, 60 * 1000);
            }
        } catch (IOException e) {
            System.err.println("Error! File doesn't exist or cannot be mapped.");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Malformed config file.");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

