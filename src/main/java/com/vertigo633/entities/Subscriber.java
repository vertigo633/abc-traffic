package com.vertigo633.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Vertigo633 on 19.06.2015.
 */
@Entity
public class Subscriber {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @OneToMany(mappedBy="subscriber")
    private List<TrafficLog> trafficLogs;

    public Subscriber(String name) {
        this.name = name;
    }

    public Subscriber() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TrafficLog> getTrafficLogs() {
        return trafficLogs;
    }

    public void setTrafficLogs(List<TrafficLog> trafficLogs) {
        this.trafficLogs = trafficLogs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
