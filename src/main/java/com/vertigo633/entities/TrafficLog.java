package com.vertigo633.entities;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by Vertigo633 on 19.06.2015.
 */
@Entity
public class TrafficLog {
    @Id
    @GeneratedValue
    private Long id;
    private Date start_time;
    private Integer loaded_data;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="subscriber_id")
    private Subscriber subscriber;

    public TrafficLog() {
    }

    public TrafficLog(Date start_time, Integer loaded_data) {
        this.start_time = start_time;
        this.loaded_data = loaded_data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLoaded_data() {
        return loaded_data;
    }

    public void setLoaded_data(Integer loaded_data) {
        this.loaded_data = loaded_data;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}
