package com.aynroot.cinemamanager.dao;

import com.aynroot.cinemamanager.domain.Hall;
import com.aynroot.cinemamanager.domain.HallRow;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class HallDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Hall> listHalls() {
        return em.createQuery("SELECT hall FROM Hall hall", Hall.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    public Hall getHall(Long id) {
        Query q = em.createQuery("SELECT hall FROM Hall hall WHERE hall.id = :id", Hall.class);
        q.setParameter("id", id);
        List<Hall> results = q.getResultList();
        return results.get(0);
    }

    @SuppressWarnings("unchecked")
    public List<HallRow> getRowsById(Long id) {
        Query q = em.createQuery("SELECT row FROM HallRow row, Hall hall " +
                "WHERE hall.id = :id AND hall.id = row.hallId", HallRow.class);
        q.setParameter("id", id);
        return q.getResultList();
    }
}
