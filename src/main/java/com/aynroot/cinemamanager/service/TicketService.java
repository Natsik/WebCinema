package com.aynroot.cinemamanager.service;

import com.aynroot.cinemamanager.dao.TicketDAO;
import com.aynroot.cinemamanager.domain.HallRow;
import com.aynroot.cinemamanager.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Transactional
    public List<Ticket> listShowTickets(Long showId) {
        return ticketDAO.listShowTickets(showId);
    }

    @Transactional
    public void createTickets(Long showId, List<HallRow> rows, Float price) {
        ticketDAO.createTickets(showId, rows, price);
    }

    @Transactional
    public void modifyTickets(Long showId, List<HallRow> rows, Float price) throws IllegalStateException {
        ticketDAO.modifyTickets(showId, rows, price);
    }

    @Transactional
    public void order(Long id) {
        ticketDAO.order(id);
    }

    @Transactional
    public Float getPrice(Long id) {
        return ticketDAO.getPrice(id);
    }
}
