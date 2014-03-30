package com.aynroot.cinemamanager.dao;

import com.aynroot.cinemamanager.domain.FilmShow;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class FilmShowDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public void addFilmShow(FilmShow filmShow) {
        FilmShow mergedFilmShow = em.merge(filmShow);

        em.persist(mergedFilmShow);
        em.flush();

        filmShow.id = mergedFilmShow.id;
    }

    public List<FilmShow> listFilmShows() {
        return em.createQuery("SELECT filmShow FROM FilmShow filmShow", FilmShow.class).getResultList();
    }
//
//    public List<FilmShow> listFilmShows(Date date) {
//        // TODO
//        return null;
//    }

    @SuppressWarnings("unchecked")
    public List<FilmShow> listFilmShows(Long hallId) {
        Query q = em.createQuery("SELECT filmShow FROM FilmShow filmShow WHERE filmShow.hallId = :hallId", FilmShow.class);
        q.setParameter("hallId", hallId);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public FilmShow getFilmShow(Long id) {
        Query q = em.createQuery("SELECT filmShow FROM FilmShow filmShow WHERE filmShow.id=:id", FilmShow.class);
        q.setParameter("id", id);
        return (FilmShow)q.getResultList().get(0);
    }

    public void removeFilmShow(Long id) {
        FilmShow filmShow = this.getFilmShow(id);
        em.remove(em.merge(filmShow));

    }
}
