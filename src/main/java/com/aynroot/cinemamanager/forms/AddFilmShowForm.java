package com.aynroot.cinemamanager.forms;

import java.sql.Timestamp;

public class AddFilmShowForm {
    private String filmId;
    private String hallId;
    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    public Timestamp getTimestampStartTime() {
        StringBuilder newStartTime = new StringBuilder(startTime);
        newStartTime.setCharAt(17, '0');
        newStartTime.setCharAt(18, '0');
        return Timestamp.valueOf(newStartTime.toString());
    }

    public String getHRStartTime() {
        return startTime.substring(0, 16);
    }

    public void setStartTime(String startTime) {

        this.startTime = startTime;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

}
