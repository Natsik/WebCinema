package com.aynroot.cinemamanager.forms;

import com.aynroot.cinemamanager.domain.FilmShow;

import java.sql.Timestamp;

public class AddFilmShowForm {
    private String filmId;
    private String hallId;
    private String startTime;
    private String price;

    public AddFilmShowForm() {}

    public AddFilmShowForm(FilmShow show, Float price) {
        filmId = show.getId().toString();
        hallId = show.getHallId().toString();
        startTime = show.getStartTime().toString();
        float p = price;
        if (p == (int)p)
            this.price = String.format("%d",(int)p);
        else
            this.price = String.format("%s", p);
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
