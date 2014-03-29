package com.aynroot.cinemamanager.forms;

public class AddFilmForm {
    private String name;
    private String description;
    private String duration;

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
