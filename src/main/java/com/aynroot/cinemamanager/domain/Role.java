package com.aynroot.cinemamanager.domain;

import javax.persistence.*;


@Entity(name="role")
public class Role {

    @Id
    private Long id;

    @OneToOne
    private Admin admin;
    private Integer role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}