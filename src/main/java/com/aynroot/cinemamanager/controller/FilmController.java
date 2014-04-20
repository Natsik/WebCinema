package com.aynroot.cinemamanager.controller;

import com.aynroot.cinemamanager.domain.Film;
import com.aynroot.cinemamanager.forms.AddFilmForm;
import com.aynroot.cinemamanager.forms.IdForm;
import com.aynroot.cinemamanager.service.FilmService;
import com.aynroot.cinemamanager.service.FilmShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FilmController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private FilmShowService filmShowService;

    @RequestMapping("/films")
    public String listFilms(Model model, @ModelAttribute("idForm") IdForm idForm) {
        model.addAttribute("film", new Film());
        model.addAttribute("filmsList", filmService.listFilms());
        model.addAttribute("idForm", new IdForm());
        return "films";
    }

    @RequestMapping(value = "/films/add", method = RequestMethod.GET)
    public String addFilmGET(Model model) {
        model.addAttribute("title", "Добавить фильм");
        model.addAttribute("canDelete", false);
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

    @RequestMapping(value = "/films/modify/{id}", method = RequestMethod.GET)
    public String modifyFilmGET(@PathVariable("id") Long id, @ModelAttribute("idForm") IdForm idForm, Model model) {
        model.addAttribute("title", "Изменить детали фильма");
        model.addAttribute("canDelete", true);
        model.addAttribute("idToDelete", id);
        Film film = filmService.getFilm(id);
        model.addAttribute("film", new AddFilmForm(film));
        model.addAttribute("idForm", new IdForm(id));
        return "add_film";
    }

    @RequestMapping(value = "/films/modify/{id}", method = RequestMethod.POST)
    public String modifyFilm(@PathVariable("id") Long id, @ModelAttribute("film") AddFilmForm form, Model model) {
        Film film = new Film();

        film.setName(form.getName());
        film.setDescription(form.getDescription());
        film.setDuration(form.getDurationSeconds());

        filmService.modifyFilm(film, id);
        return "redirect:/films";
    }

    @RequestMapping(value = "/films/delete", method = RequestMethod.POST)
    public String removeFilm(@ModelAttribute("idToDelete") IdForm idForm, Model model) {
        filmService.removeFilm(Long.parseLong(idForm.getId()));
        return "redirect:/films";
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.GET)
    public String showFilm(@PathVariable("id") Long id, @ModelAttribute("idForm") IdForm idForm, ModelMap model) {
        model.addAttribute("film", new Film());
        model.addAttribute("requestedFilm", filmService.getFilm(id));
        List<Object[]> shows = filmShowService.listFilmShowsByFilmIdByDayOffest(id, 0);
        model.addAttribute("shows", shows);
        model.addAttribute("curDayOffset", 0);
        model.addAttribute("idForm", new IdForm(id));
        return "film";
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.POST)
    public String showFilm(@PathVariable("id") Long id, @RequestParam("day") Integer dayOffset,
                           @ModelAttribute("idForm") IdForm idForm, ModelMap model) {
        model.addAttribute("film", new Film());
        model.addAttribute("requestedFilm", filmService.getFilm(id));
        model.addAttribute("shows", filmShowService.listFilmShowsByFilmIdByDayOffest(id, dayOffset));
        model.addAttribute("curDayOffset", dayOffset);
        model.addAttribute("idForm", new IdForm(id));
        return "film";
    }
}