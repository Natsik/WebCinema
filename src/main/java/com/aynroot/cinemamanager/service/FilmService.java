package com.aynroot.cinemamanager.service;

import com.aynroot.cinemamanager.dao.FilmDAO;
import com.aynroot.cinemamanager.domain.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmDAO filmDAO;

    @Transactional
    public void addFilm(Film film) {
        filmDAO.addFilm(film);
    }

    @Transactional
    public List<Film> listFilms() {
        return filmDAO.listFilms();
    }

    @Transactional
    public void removeFilm(Long id) {
        filmDAO.removeFilm(id);
    }

    @Transactional
    public Film getFilm(Long id) {
        return filmDAO.getFilm(id);
    }

    @Transactional
    public void modifyFilm(Film film, Long id) {
        filmDAO.modifyFilm(film, id);
    }
}
