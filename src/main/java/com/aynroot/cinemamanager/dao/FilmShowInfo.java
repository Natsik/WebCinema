package com.aynroot.cinemamanager.dao;


import java.sql.Timestamp;

public class FilmShowInfo {
    //s.id, s.start_time, h.name, f.id, f.name, f.duration
    Integer showId;
    Timestamp showStartTime;
    String hallName;
    Integer filmId;
    String filmName;
    Integer filmDuration;

    public FilmShowInfo() {}

    public FilmShowInfo(Object[] showInfo) {
        showId = (Integer)showInfo[0];
        showStartTime = (Timestamp)showInfo[1];
        hallName = (String)showInfo[2];
        filmId = (Integer)showInfo[3];
        filmName = (String)showInfo[4];
        filmDuration = (Integer)showInfo[5];
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Timestamp getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(Timestamp showStartTime) {
        this.showStartTime = showStartTime;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getFilmDuration() {
        return filmDuration;
    }

    public void setFilmDuration(Integer filmDuration) {
        this.filmDuration = filmDuration;
    }
}
