package com.aynroot.cinemamanager.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "filmshow")
public class FilmShow {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "start_time")
    public Timestamp startTime;

    @Column(name = "film_id")
    public Long filmId;

    @Column(name = "hall_id")
    public Long hallId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }
}
