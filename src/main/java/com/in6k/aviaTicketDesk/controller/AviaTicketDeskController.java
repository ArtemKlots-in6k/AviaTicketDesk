package com.in6k.aviaTicketDesk.controller;

import com.in6k.aviaTicketDesk.dao.CityDAO;
import com.in6k.aviaTicketDesk.dao.FlightDAO;
import com.in6k.aviaTicketDesk.dao.PassengerDAO;
import com.in6k.aviaTicketDesk.dao.TicketDAO;
import com.in6k.aviaTicketDesk.service.AviaTicketDesk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by employee on 8/2/16.
 */
@Controller
@RequestMapping
public class AviaTicketDeskController {

    @Autowired
    AviaTicketDesk aviaTicketDesk;

    @Autowired
    CityDAO cityDAO;

    @Autowired
    FlightDAO flightDAO;

    @Autowired
    TicketDAO ticketDAO;

    @Autowired
    PassengerDAO passengerDAO;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public String flights(ModelMap model) {


        model.put("flights", aviaTicketDesk.getAllFlights());
        return "flights";
    }

    @RequestMapping(value = "/ticketDesk", method = RequestMethod.GET)
    public String ticketDeskGET(ModelMap model) {


        model.put("flights", aviaTicketDesk.getAllFlights());
        return "ticketDesk";
    }

    @RequestMapping(value = "/ticketDesk", method = RequestMethod.POST)
    public String ticketDeskPOST(
            ModelMap model,
            @RequestParam("flight") int flightId,
            @RequestParam("numberOfTickets") int numberOfTickets,
            @RequestParam("passenger") String passenger
    ) {

//        aviaTicketDesk.buyTickets(flightDAO.getFlightById(flightId), passengerDAO.getUserById(passenger), numberOfTickets);

        model.put("flight", flightDAO.getFlightById(flightId));
        model.put("numberOfTickets", numberOfTickets);
        model.put("passenger", passengerDAO.getUserById(1).getName());
        return "ticketInfo";
    }

}
