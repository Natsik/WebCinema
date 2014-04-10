package com.aynroot.cinemamanager.domain;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    private Long id;

    private String name;

    @Column(unique=true)
    private String username;
    private String password;

    @OneToOne(mappedBy="admin", cascade={CascadeType.ALL})
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
