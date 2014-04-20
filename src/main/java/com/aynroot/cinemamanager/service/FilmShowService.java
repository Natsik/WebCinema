package com.aynroot.cinemamanager.service;

import com.aynroot.cinemamanager.dao.FilmShowDAO;
import com.aynroot.cinemamanager.dao.FilmShowInfo;
import com.aynroot.cinemamanager.domain.FilmShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FilmShowService {

    @Autowired
    private FilmShowDAO filmShowDAO;

    @Transactional
    public Long addFilmShow(FilmShow filmShow) {
        return filmShowDAO.addFilmShow(filmShow);
    }

    @Transactional
    public void modifyFilmShow(FilmShow filmShow, Long id) {
        filmShowDAO.modifyFilmShow(filmShow, id);
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
    public List<Object[]> listFilmShowsByFilmIdByDayOffest(Long filmId, Integer dayOffset) {
        return filmShowDAO.listFilmShowsByFilmIdByDayOffest(filmId, dayOffset);
    }

    @Transactional
    public Boolean checkTimeAvaiability(Timestamp ts, Long hallId) { return filmShowDAO.checkTimeAvailability(ts, hallId); }

    @Transactional
    public List<Object[]> listFilmShowsByDayOffest(Integer dayOffset) { return filmShowDAO.listFilmShowsByDayOffest(dayOffset); }
}
