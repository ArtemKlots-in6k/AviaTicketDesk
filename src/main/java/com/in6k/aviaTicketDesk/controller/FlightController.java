package com.in6k.aviaTicketDesk.controller;

import com.in6k.aviaTicketDesk.dao.AirportDAO;
import com.in6k.aviaTicketDesk.dao.CityDAO;
import com.in6k.aviaTicketDesk.dao.FlightDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

/**
 * Created by employee on 8/1/16.
 */
public class FlightController extends ApplicationController {

//    @Autowired
//    private AviaTicketDesk aviaTicketDesk;

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private AirportDAO airportDAO;

    @Autowired
    private FlightDAO flightDAO;


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        flightDAO = ApplicationContext.getBean(FlightDAO.class);

        request.setAttribute("flights", flightDAO.getAll());

        request.getRequestDispatcher("jsp/flights.jsp").forward(request, response);
    }

    public void destroy() {

    }
}
