package com.in6k.aviaTicketDesk.service;

import com.in6k.aviaTicketDesk.dao.FlightDAO;
import com.in6k.aviaTicketDesk.dao.TicketDAO;
import com.in6k.aviaTicketDesk.entity.Flight;
import com.in6k.aviaTicketDesk.entity.Passenger;

import java.util.List;

/**
 * Created by employee on 7/29/16.
 */
public class AviaTicketDesk {
    private TicketDAO ticketDAO;
    private FlightDAO flightDAO;

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public TicketDAO getTicketDAO() {
        return ticketDAO;
    }

    public void setFlightDAO(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    public FlightDAO getFlightDAO() {
        return flightDAO;
    }

    public List getAllFlights() {
        return flightDAO.getAll();
    }

    public void buyTickets(Flight flight, Passenger passenger, int numberOfTickets) {
        try {
            flightDAO.reserveSeats(flight, numberOfTickets);

            System.out.println(flight);
            for (int i = 0; i < numberOfTickets; i++) {
                ticketDAO.create(flight, passenger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
