package com.aynroot.cinemamanager.dao;


import com.aynroot.cinemamanager.domain.Admin;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class AdminDAO {

    @PersistenceContext
    private EntityManager em;

    public Admin findByUsername(String username) {
        Query q = em.createQuery("SELECT a FROM Admin a WHERE a.username=:username", Admin.class);
        q.setParameter("username", username);
        return (Admin)q.getResultList().get(0);
    }
}
