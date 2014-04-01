package com.aynroot.cinemamanager.controller;

import com.aynroot.cinemamanager.domain.Film;
import com.aynroot.cinemamanager.forms.AddFilmForm;
import com.aynroot.cinemamanager.service.FilmService;
import com.aynroot.cinemamanager.service.FilmShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class FilmController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private FilmShowService filmShowService;

    @RequestMapping("/films")
    public String listFilms(ModelMap model) {
        model.put("film", new Film());
        model.put("filmsList", filmService.listFilms());
        return "films";
    }

    @RequestMapping(value = "/films/add", method = RequestMethod.GET)
    public String addFilmGET(Model model) {
        model.addAttribute("film", new AddFilmForm());
        return "add_film";
    }

    @RequestMapping(value = "/films/add", method = RequestMethod.POST)
    public String addFilm(@ModelAttribute("film") AddFilmForm form, Model model) {

        Film film = new Film();

        film.setName(form.getName());
        film.setDescription(form.getDescription());
        film.setDuration(form.getDurationSeconds());

        filmService.addFilm(film);
        model.addAttribute("message", "Добавлен фильм <a href='/films/" + film.getId().toString() + "'>" + form.getName() + "</a>");
        model.addAttribute("film", new AddFilmForm());

        return "add_film";
    }

    @RequestMapping("/films/{id}")
    public String showFilm(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("film", new Film());
        model.addAttribute("requestedFilm", filmService.getFilm(id));
        model.addAttribute("shows", filmShowService.listFilmShowsByFilmId(id));
        return "film";
    }
}