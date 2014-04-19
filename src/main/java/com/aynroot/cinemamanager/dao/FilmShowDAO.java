package com.aynroot.cinemamanager.dao;

import com.aynroot.cinemamanager.domain.FilmShow;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class FilmShowDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public Long addFilmShow(FilmShow filmShow) {
        FilmShow mergedFilmShow = em.merge(filmShow);

        em.persist(mergedFilmShow);
        em.flush();

        filmShow.id = mergedFilmShow.id;
        return filmShow.id;
    }

    public List<FilmShow> listFilmShows() {
        return em.createQuery("SELECT filmShow FROM FilmShow filmShow", FilmShow.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> listFilmShowsByDayOffest(Integer dayOffset) {
        Query q = em.createNativeQuery("SELECT s.id as showId, s.start_time, h.name as hallName, f.id as filmId, f.name as filmName, f.duration " +
                "FROM filmshow s, film f, cinemahall h " +
                "WHERE s.start_time >= DATE_ADD(CURDATE(), INTERVAL :dayOffset DAY) and " +
                "s.start_time <= DATE_ADD(CURDATE(), INTERVAL :dayOffset + 1 DAY) and " +
                "f.id = s.film_id and h.id = s.hall_id " +
                "ORDER BY s.start_time");
        q.setParameter("dayOffset", dayOffset);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<FilmShow> listFilmShowsByHallId(Long hallId) {
        Query q = em.createQuery("SELECT filmShow FROM FilmShow filmShow WHERE filmShow.hallId = :hallId", FilmShow.class);
        q.setParameter("hallId", hallId);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> listFilmShowsByFilmId(Long filmId) {
        Query q = em.createQuery("SELECT filmShow.id, filmShow.startTime, hall.name FROM FilmShow filmShow, Hall hall " +
                "WHERE filmShow.filmId = :filmId AND filmShow.hallId = hall.id " +
                "AND filmShow.startTime > current_timestamp() ORDER BY filmShow.startTime ASC");
        q.setParameter("filmId", filmId);
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

    public Boolean checkTimeAvailability(Timestamp ts, Long hallId) {
        Query q = em.createNativeQuery("SELECT fs.id FROM filmshow fs, film f WHERE fs.start_time <= :ts and " +
                "f.id = fs.film_id and fs.start_time + INTERVAL f.duration SECOND >= :ts and fs.hall_id = :hallId");
        q.setParameter("ts", ts);
        q.setParameter("hallId", hallId);
        return q.getResultList().isEmpty();
    }
}
