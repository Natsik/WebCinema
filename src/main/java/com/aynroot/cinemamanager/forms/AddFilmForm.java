package com.aynroot.cinemamanager.forms;

import com.aynroot.cinemamanager.domain.Film;

public class AddFilmForm {
    private String name;
    private String description;
    private String duration;

    public AddFilmForm() {}

    public AddFilmForm(Film film) {
        this.name = film.getName();
        this.description = film.getDescription();

        Integer d = film.getDuration();
        Integer hours = d / (60 * 60);
        Integer minutes = (d - hours * 60 * 60) / 60;
        this.duration = String.format("%02d", hours) + ':' + String.format("%02d", minutes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getDurationSeconds() {
        String[] parts = duration.split(":");
        Integer hours = Integer.parseInt(parts[0]);
        Integer minutes = Integer.parseInt(parts[1]);
        return hours * 60 * 60 + minutes * 60;
    }
}
