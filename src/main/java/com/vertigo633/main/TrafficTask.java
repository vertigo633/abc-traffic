package com.vertigo633.main;

import com.vertigo633.dao.TrafficLogDAO;
import com.vertigo633.dao.TrafficLogDAOImpl;
import com.vertigo633.entities.Subscriber;
import com.vertigo633.entities.TrafficLog;
import com.vertigo633.service.TrafficUtils;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

/**
 * Created by Vertigo633 on 22.06.2015.
 */
public class TrafficTask extends TimerTask {

    TrafficLogDAO trafficLogDAO = new TrafficLogDAOImpl();
    Long stopTime;
    Semaphore semaphore;
    Subscriber subscriber;

    public TrafficTask(Subscriber subscriber, Date end_date, Semaphore semaphore) {
        this.stopTime = end_date.getTime();
        this.semaphore = semaphore;
        this.subscriber = subscriber;

    }

    @Override
    public void run() {
        if (System.currentTimeMillis() > stopTime) {
            return;
        }
        int loadedBytes = TrafficUtils.getLoadedBytes();
        Date start_time = new Date();

        TrafficLog trafficLog = new TrafficLog(start_time, loadedBytes);
        trafficLog.setSubscriber(subscriber);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        trafficLogDAO.saveLog(trafficLog);
        semaphore.release();

    }
}
