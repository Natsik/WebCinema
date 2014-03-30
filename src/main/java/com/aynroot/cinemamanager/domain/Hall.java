package com.aynroot.cinemamanager.domain;

import javax.persistence.*;

@Entity
@Table(name = "cinemahall")
public class Hall {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "capacity")
    public Integer capacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
