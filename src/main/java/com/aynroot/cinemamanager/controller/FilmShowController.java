package com.aynroot.cinemamanager.controller;

import com.aynroot.cinemamanager.dao.FilmShowInfo;
import com.aynroot.cinemamanager.domain.*;
import com.aynroot.cinemamanager.forms.AddFilmForm;
import com.aynroot.cinemamanager.forms.AddFilmShowForm;
import com.aynroot.cinemamanager.forms.IdForm;
import com.aynroot.cinemamanager.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return listDayFilmShows(0, model);
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String listDayFilmShows(@RequestParam("day") Integer dayOffset, Model model) {
        List<Object[]> info = filmShowService.listFilmShowsByDayOffest(dayOffset);
        List<FilmShowInfo> showsInfos = new LinkedList<FilmShowInfo>();
        for (Object[] obj : info) {
            showsInfos.add(new FilmShowInfo(obj));
        }
        model.addAttribute("showInfo", new FilmShowInfo());
        model.addAttribute("showsInfoList", showsInfos);
        model.addAttribute("curDayOffset", dayOffset);
        return "filmshows";
    }

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

    @RequestMapping(value = "/filmshows/delete", method = RequestMethod.POST)
    public String deleteFilmShow(Long id) {
        filmShowService.removeFilmShow(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/filmshows/modify/{id}", method = RequestMethod.GET)
    public String modifyFilmGET(@PathVariable("id") Long id, @ModelAttribute("idForm") IdForm idForm, Model model) {
        model.addAttribute("title", "Изменить детали сеанса");
        model.addAttribute("canDelete", true);
        model.addAttribute("idToDelete", id);
        FilmShow show = filmShowService.getFilmShow(id);
        Float price = ticketService.getPrice(id);
        model.addAttribute("idForm", new IdForm(id));
        initModelAddFilmShow(model);
        model.addAttribute("filmShow", show);
        model.addAttribute("price", price);
        return "add_filmshow";
    }

    @RequestMapping(value = "/filmshows/modify/{id}", method = RequestMethod.POST)
    public String modifyFilmShow(@PathVariable("id") Long id, @ModelAttribute("filmShow") AddFilmShowForm form, Model model) {
        if (!filmShowService.checkTimeAvaiability(form.getTimestampStartTime(), Long.parseLong(form.getHallId()))) {
            model.addAttribute("error_message", "Это время недоступно, идет другой сеанс.");
            initModelAddFilmShow(model);
        } else {
            FilmShow filmShow = new FilmShow();
            initModelAddFilmShow(model);

            filmShow.setFilmId(Long.parseLong(form.getFilmId()));
            filmShow.setHallId(Long.parseLong(form.getHallId()));
            filmShow.setStartTime(form.getTimestampStartTime());

            filmShowService.modifyFilmShow(filmShow, id);
            List<HallRow> rows = hallService.getRowsById(Long.parseLong(form.getHallId()));
            try {
                ticketService.modifyTickets(id, rows, Float.parseFloat(form.getPrice()));
            } catch (IllegalStateException e) {
                model.addAttribute("error_message", "Это время недоступно, идет другой сеанс.");
                return "add_filmshow";
            }
        }
        return "redirect:/";
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