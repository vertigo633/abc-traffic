package com.vertigo633.dao;

import com.vertigo633.entities.Subscriber;

import java.util.List;

/**
 * Created by Vertigo633 on 22.06.2015.
 */
public interface SubscriberDAO {

    Subscriber getSubscriber(String subscriberName);

    void save(Subscriber subscriber);

    List<Subscriber> getSubscribers();

    void update(Subscriber subscriber);

}
