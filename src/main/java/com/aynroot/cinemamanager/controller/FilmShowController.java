package com.aynroot.cinemamanager.controller;

import com.aynroot.cinemamanager.domain.Film;
import com.aynroot.cinemamanager.domain.FilmShow;
import com.aynroot.cinemamanager.domain.Hall;
import com.aynroot.cinemamanager.forms.AddFilmShowForm;
import com.aynroot.cinemamanager.service.FilmService;
import com.aynroot.cinemamanager.service.FilmShowService;
import com.aynroot.cinemamanager.service.HallService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.util.logging.resources.logging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class FilmShowController {

    @Autowired
    private FilmShowService filmShowService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private HallService hallService;

    public static final Logger logger= Logger.getLogger(FilmShowService.class);

//    @RequestMapping("/filmshows")
//    public String listFilmShows(ModelMap model) {
//        model.put("filmShow", new FilmShow());
//        model.put("filmShowsList", filmShowService.listFilmShows());
//        return "filmShows";
//    }
//
//    @RequestMapping(value="/filmshows", params = {"from", "till"}, method=RequestMethod.GET)
//    public String listFilmShows(@RequestParam(value = "from") String fromDate,
//                                @RequestParam(value = "till") String tillDate) {
//
//        return "filmShows";
//    }

    @RequestMapping(value = "/filmshows/add", method = RequestMethod.GET)
    public String addFilmShowGET(Model model) {
        model.addAttribute("filmShow", new AddFilmShowForm());

        Map<String, String> filmsListMap = new LinkedHashMap<String, String>();
        for (Film film : filmService.listFilms()) {
            filmsListMap.put(film.getId().toString(), film.getName());
        }
        model.addAttribute("filmsList", filmsListMap);

        Map<String, String> hallsListMap = new LinkedHashMap<String, String>();
        for (Hall hall : hallService.listHalls()) {
            hallsListMap.put(hall.getId().toString(), hall.getName());
        }
        model.addAttribute("hallsList", hallsListMap);

        return "add_filmshow";
    }

    @RequestMapping(value = "/filmshows/add", method = RequestMethod.POST)
    public String addFilmShow(@ModelAttribute("filmShow") AddFilmShowForm form, Model model) {

        FilmShow filmShow = new FilmShow();

        filmShow.setFilmId(Long.parseLong(form.getFilmId()));
        filmShow.setHallId(Long.parseLong(form.getHallId()));
        logger.info(form.getStartTime());
        filmShow.setStartTime(form.getTimestampStartTime());

        filmShowService.addFilmShow(filmShow);

        model.addAttribute("message", "Добавлен сеанс " + form.getHRStartTime() +
                " на фильм <a href='/films/" + filmShow.getFilmId().toString() + "'>" +
                filmService.getFilm(filmShow.getFilmId()).getName() + "</a>");
        model.addAttribute("filmShow", new AddFilmShowForm());

        return "add_filmshow";
    }
}