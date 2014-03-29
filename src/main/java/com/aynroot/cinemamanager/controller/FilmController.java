package com.aynroot.cinemamanager.controller;

import com.aynroot.cinemamanager.domain.Film;
import com.aynroot.cinemamanager.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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