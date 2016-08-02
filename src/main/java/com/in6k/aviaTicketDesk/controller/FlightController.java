package com.in6k.aviaTicketDesk.controller;

import com.in6k.aviaTicketDesk.dao.CityDAO;
import com.in6k.aviaTicketDesk.service.AviaTicketDesk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by employee on 8/2/16.
 */
@Controller
@RequestMapping
public class FlightController {

    @Autowired
    AviaTicketDesk aviaTicketDesk;

    @Autowired
    CityDAO cityDAO;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        System.out.println(aviaTicketDesk.getAllFlights());
        System.out.println(cityDAO.getAll());

        model.put("flights", aviaTicketDesk.getAllFlights());
        return "flights";
    }
}
