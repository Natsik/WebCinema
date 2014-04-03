package com.aynroot.cinemamanager.service;

import com.aynroot.cinemamanager.dao.HallRowDAO;
import com.aynroot.cinemamanager.domain.HallRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class HallRowService {
    @Autowired
    private HallRowDAO hallRowDAO;

    @Transactional
    public HallRow getHallRow(Long id) {
        return hallRowDAO.getHallRow(id);
    }
}

