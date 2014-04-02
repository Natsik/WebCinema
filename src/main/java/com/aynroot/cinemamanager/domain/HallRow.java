package com.aynroot.cinemamanager.domain;

import javax.persistence.*;

@Entity
@Table(name = "hallrow")
public class HallRow {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "n_seats")
    public Integer nSeats;

    @Column(name = "number")
    public Integer number;

    @Column(name = "hall_id")
    public Long hallId;

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getnSeats() {
        return nSeats;
    }

    public void setnSeats(Integer nSeats) {
        this.nSeats = nSeats;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
