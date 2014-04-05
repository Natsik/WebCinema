package com.aynroot.cinemamanager.controller;

import com.aynroot.cinemamanager.domain.FilmShow;
import com.aynroot.cinemamanager.domain.HallRow;
import com.aynroot.cinemamanager.domain.Ticket;
import com.aynroot.cinemamanager.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TicketController {
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


    @RequestMapping("/buy_ticket/{id}")
    public String buyTicket(@PathVariable("id") Long id, ModelMap model) {

        return "buy_ticket";
    }
}
