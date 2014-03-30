package com.aynroot.cinemamanager.dao;

import com.aynroot.cinemamanager.domain.Hall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HallDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Hall> listHalls() {
        return em.createQuery("SELECT hall FROM Hall hall", Hall.class).getResultList();
    }
}
