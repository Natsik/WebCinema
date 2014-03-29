package com.aynroot.cinemamanager.controller;

import com.aynroot.cinemamanager.dao.FilmDAO;
import com.aynroot.cinemamanager.domain.Film;
import com.aynroot.cinemamanager.forms.AddFilmForm;
import com.aynroot.cinemamanager.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FilmController {

    @Autowired
    private FilmService filmService;

    @RequestMapping("/films")
    public String listFilms(ModelMap model) {
        model.put("film", new Film());
        model.put("filmsList", filmService.listFilms());
        return "films";
    }

    @ModelAttribute("add_film")
    public AddFilmForm getAddFilmForm() {
        return new AddFilmForm();
    }

    @RequestMapping(value = "/films/add", method = RequestMethod.GET)
    public String addFilmGET() {
        return "add_film";
    }

    @RequestMapping(value = "/films/add", method = RequestMethod.POST)
    public String addFilm(@ModelAttribute("add_film") AddFilmForm form) {

        Film film = new Film();

        film.setName(form.getName());
        film.setDescription(form.getDescription());
        film.setDuration(form.getDurationSeconds());

        filmService.addFilm(film);

        return "add_film";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/films";
    }

    @RequestMapping("/films/{id}")
    public String showFilm(@PathVariable("id") Long id, ModelMap model) {
        model.put("film", new Film());
        model.put("requestedFilm", filmService.getFilm(id));
        return "film";
    }
}