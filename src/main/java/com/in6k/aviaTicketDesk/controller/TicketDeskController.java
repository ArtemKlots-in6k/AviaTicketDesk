package com.in6k.aviaTicketDesk.controller;

import com.in6k.aviaTicketDesk.dao.FlightDAO;
import com.in6k.aviaTicketDesk.dao.PassengerDAO;
import com.in6k.aviaTicketDesk.service.AviaTicketDesk;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by employee on 8/2/16.
 */
public class TicketDeskController extends ApplicationController {
    @Autowired
    private FlightDAO flightDAO;

    @Autowired
    private PassengerDAO passengerDAO;

    @Autowired
    private AviaTicketDesk aviaTicketDesk;


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        flightDAO = ApplicationContext.getBean(FlightDAO.class);
        passengerDAO = ApplicationContext.getBean(PassengerDAO.class);

        request.setAttribute("flights", flightDAO.getAll());

        request.getRequestDispatcher("jsp/ticketDesk.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        flightDAO = ApplicationContext.getBean(FlightDAO.class);
        passengerDAO = ApplicationContext.getBean(PassengerDAO.class);
        aviaTicketDesk = ApplicationContext.getBean(AviaTicketDesk.class);

        try {
            aviaTicketDesk.buyTickets(
                    flightDAO.getFlightById(Integer.parseInt(request.getParameter("flight"))),
                    passengerDAO.create(request.getParameter("passenger")),
                    Integer.parseInt(request.getParameter("numberOfTickets"))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
