package com.aynroot.cinemamanager.controller;

import com.aynroot.cinemamanager.domain.*;
import com.aynroot.cinemamanager.forms.AddFilmShowForm;
import com.aynroot.cinemamanager.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
public class FilmShowController {

    @Autowired
    private FilmShowService filmShowService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private HallService hallService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private HallRowService hallRowService;

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

    private void initModelAddFilmShow(Model model) {
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
    }

    @RequestMapping(value = "/filmshows/add", method = RequestMethod.GET)
    public String addFilmShowGET(Model model) {

        initModelAddFilmShow(model);
        return "add_filmshow";
    }

    @RequestMapping(value = "/filmshows/add", method = RequestMethod.POST)
    public String addFilmShow(@ModelAttribute("filmShow") AddFilmShowForm form, Model model) {
        if (!filmShowService.checkTimeAvaiability(form.getTimestampStartTime(), Long.parseLong(form.getHallId()))) {
            model.addAttribute("error_message", "Это время недоступно, идет другой сеанс.");
        } else {
            FilmShow filmShow = new FilmShow();

            filmShow.setFilmId(Long.parseLong(form.getFilmId()));
            filmShow.setHallId(Long.parseLong(form.getHallId()));
            logger.info(form.getStartTime());
            filmShow.setStartTime(form.getTimestampStartTime());

            Long filmShowId = filmShowService.addFilmShow(filmShow);
            List<HallRow> rows = hallService.getRowsById(Long.parseLong(form.getHallId()));
            ticketService.createTickets(filmShowId, rows, Float.parseFloat(form.getPrice()));

            model.addAttribute("message", "Добавлен сеанс " + form.getHRStartTime() +
                    " на фильм <a href='/films/" + filmShow.getFilmId().toString() + "'>" +
                    filmService.getFilm(filmShow.getFilmId()).getName() + "</a>");
        }
        initModelAddFilmShow(model);
        return "add_filmshow";
    }

    @RequestMapping("/filmshow/{id}")
    public String showFilm(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("filmshow", new FilmShow());

        FilmShow requestedFilmShow = filmShowService.getFilmShow(id);
        List<Ticket> tickets = ticketService.listShowTickets(id);
        List<HallRow> rows = new ArrayList<HallRow>();
        Long prevId = 0L;
        for (Ticket ticket : tickets) {
            if (!prevId.equals(hallRowService.getHallRow(ticket.rowId).id))
                rows.add(hallRowService.getHallRow(ticket.rowId));
            prevId = ticket.rowId;
        }

        model.addAttribute("requestedFilmShow", requestedFilmShow);
        model.addAttribute("requestedFilm", filmService.getFilm(requestedFilmShow.filmId));
        model.addAttribute("tickets", tickets);
        model.addAttribute("price", tickets.get(0).price);
        model.addAttribute("hall", hallService.getHall(rows.get(0).hallId));
        model.addAttribute("rows", rows);
        model.addAttribute("rowLength", rows.get(0).getnSeats());
        return "filmshow";
    }
}