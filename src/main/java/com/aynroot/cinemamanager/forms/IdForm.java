package com.aynroot.cinemamanager.forms;


public class IdForm {
    String id;

    public IdForm() {}

    public IdForm(Long id) {
        this.id = String.valueOf(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
