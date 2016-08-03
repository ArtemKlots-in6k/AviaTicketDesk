package com.in6k.aviaTicketDesk.service;

import com.in6k.aviaTicketDesk.dao.FlightDAO;
import com.in6k.aviaTicketDesk.entity.Airport;
import com.in6k.aviaTicketDesk.entity.Flight;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by employee on 8/3/16.
 */
// TODO: 8/3/16 TESTS!!!!
public class FlightService {

    private FlightDAO flightDAO;

    public void setFlightDAO(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    public FlightDAO getFlightDAO() {
        return flightDAO;
    }

    public Flight getById(int id) {
        return flightDAO.getFlightById(id);
    }

    public List getAll() {
        return flightDAO.getAll();
    }

    public Flight create(Airport departureAirport,
                         Airport destinationAirport,
                         int aircraftCapacity,
                         LocalDateTime departureDateTime) {
        return flightDAO.create(departureAirport, destinationAirport, aircraftCapacity, departureDateTime);
    }
}
