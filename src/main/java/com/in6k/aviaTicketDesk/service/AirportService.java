package com.in6k.aviaTicketDesk.service;

import com.in6k.aviaTicketDesk.dao.AirportDAO;
import com.in6k.aviaTicketDesk.entity.Airport;
import com.in6k.aviaTicketDesk.entity.City;

import java.util.List;

/**
 * Created by employee on 8/3/16.
 */
public class AirportService {
    private AirportDAO airportDAO;

    public void setAirportDAO(AirportDAO airportDAO) {
        this.airportDAO = airportDAO;
    }

    public AirportDAO getAirportDAO() {
        return airportDAO;
    }

    public List getAll() {
        return airportDAO.getAll();
    }

    public Airport getById(int id) {
        return airportDAO.getAirportById(id);
    }

    public Airport create(String title, City city) {
        return airportDAO.create(title, city);
    }
}
