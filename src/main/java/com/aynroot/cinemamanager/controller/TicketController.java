package com.aynroot.cinemamanager.controller;

import com.aynroot.cinemamanager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "/buy_ticket", method = RequestMethod.POST)
    public String buyTicket(@RequestParam("id") String ticketId, ModelMap model) {
        ticketService.order(Long.parseLong(ticketId));
        return "buy_ticket";
    }
}
