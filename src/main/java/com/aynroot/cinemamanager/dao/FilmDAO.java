package com.aynroot.cinemamanager.dao;

import com.aynroot.cinemamanager.domain.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FilmDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public void addFilm(Film film) {
        Film mergedFilm = em.merge(film);

        em.persist(mergedFilm);
        em.flush();

        film.id = mergedFilm.id;
    }

    public List<Film> listFilms() {
        return em.createQuery("SELECT film FROM Film film", Film.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    public Film getFilm(Long id) {
        Query q = em.createQuery("SELECT film FROM Film film WHERE film.id=:id", Film.class);
        q.setParameter("id", id);
        return (Film)q.getResultList().get(0);
    }

    public void removeFilm(Long id) {
        Film film = this.getFilm(id);
        em.remove(em.merge(film));
    }

    public void modifyFilm(Film film, Long id) {
        Film filmToUpdate = em.find(Film.class, id);
        filmToUpdate.setDescription(film.getDescription());
        filmToUpdate.setDuration(film.getDuration());
        filmToUpdate.setName(film.getName());
    }
}
