package com.aynroot.cinemamanager.dao;

import com.aynroot.cinemamanager.domain.HallRow;
import com.aynroot.cinemamanager.domain.Ticket;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class HallRowDAO {
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public HallRow getHallRow(Long id) {
        Query q = em.createQuery("SELECT row FROM HallRow row WHERE row.id = :id", HallRow.class);
        q.setParameter("id", id);
        List<HallRow> results = q.getResultList();
        return results.get(0);
    }
}
