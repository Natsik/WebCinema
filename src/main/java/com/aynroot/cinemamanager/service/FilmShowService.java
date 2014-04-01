package com.aynroot.cinemamanager.service;

import com.aynroot.cinemamanager.dao.FilmShowDAO;
import com.aynroot.cinemamanager.domain.FilmShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmShowService {

    @Autowired
    private FilmShowDAO filmShowDAO;

    @Transactional
    public void addFilmShow(FilmShow filmShow) {
        filmShowDAO.addFilmShow(filmShow);
    }

    @Transactional
    public List<FilmShow> listFilmShows() {
        return filmShowDAO.listFilmShows();
    }

    @Transactional
    public void removeFilmShow(Long id) {
        filmShowDAO.removeFilmShow(id);
    }

    @Transactional
    public FilmShow getFilmShow(Long id) {
        return filmShowDAO.getFilmShow(id);
    }

    @Transactional
    public List<Object[]> listFilmShowsByFilmId(Long id) {
        return filmShowDAO.listFilmShowsByFilmId(id);
    }
}
