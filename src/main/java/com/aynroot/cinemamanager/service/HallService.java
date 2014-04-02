package com.aynroot.cinemamanager.service;

import com.aynroot.cinemamanager.dao.HallDAO;
import com.aynroot.cinemamanager.domain.Hall;
import com.aynroot.cinemamanager.domain.HallRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HallService {

    @Autowired
    private HallDAO hallDAO;

    @Transactional
    public List<Hall> listHalls() {
        return hallDAO.listHalls();
    }

    @Transactional
    public Hall getHall(Long id) {
        return hallDAO.getHall(id);
    }

    @Transactional
    public List<HallRow> getRowsById(Long id) {
        return hallDAO.getRowsById(id);
    }
}
