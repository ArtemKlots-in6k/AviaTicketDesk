package com.in6k.aviaTicketDesk.controller;

import com.in6k.aviaTicketDesk.dao.CityDAO;
import com.in6k.aviaTicketDesk.dao.PassengerDAO;
import com.in6k.aviaTicketDesk.dao.TicketDAO;
import com.in6k.aviaTicketDesk.form.BuyTicketForm;
import com.in6k.aviaTicketDesk.service.AviaTicketDesk;
import com.in6k.aviaTicketDesk.service.FlightService;
import com.in6k.aviaTicketDesk.validator.BuyTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    FlightService flightService;

    @Autowired
    TicketDAO ticketDAO;

    @Autowired
    PassengerDAO passengerDAO;

    @Autowired
    BuyTicketValidator buyTicketValidator;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public String flights(ModelMap model) {


        model.put("flights", aviaTicketDesk.getAllFlights());
        return "flights";
    }

    @RequestMapping(value = "/ticketDesk", method = RequestMethod.GET)
    public String ticketDeskGET(ModelMap model) {

        BuyTicketForm buyTicketForm = new BuyTicketForm();
        model.put("buyTicketForm", buyTicketForm);

        model.put("flights", aviaTicketDesk.getAllFlights());
        return "ticketDesk";
    }

    @RequestMapping(value = "/ticketDesk", method = RequestMethod.POST)
    public String ticketDeskPOST(
            ModelMap model,
            BuyTicketForm buyTicketForm,
            BindingResult result,
            @RequestParam("flight") int flightId,
            @RequestParam("numberOfTickets") int numberOfTickets,
            @RequestParam("passengerName") String passengerName
    ) {

        buyTicketValidator.validate(buyTicketForm, result);
        if (result.hasErrors()) {
            model.put("flights", aviaTicketDesk.getAllFlights());
            return "ticketDesk";
        }
        aviaTicketDesk.buyTickets(flightService.getById(flightId), passengerDAO.getByName(passengerName), numberOfTickets);

        model.put("flight", flightService.getById(flightId));
        model.put("numberOfTickets", numberOfTickets);
        model.put("passengerName", passengerDAO.getByName(passengerName).getName());
        return "ticketInfo";
    }

}
