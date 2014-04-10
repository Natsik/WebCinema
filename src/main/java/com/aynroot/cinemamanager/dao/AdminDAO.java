package com.aynroot.cinemamanager.dao;


import com.aynroot.cinemamanager.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDAO extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);
}
