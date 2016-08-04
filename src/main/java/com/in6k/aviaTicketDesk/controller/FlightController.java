package com.in6k.aviaTicketDesk.controller;

import com.in6k.aviaTicketDesk.entity.Flight;
import com.in6k.aviaTicketDesk.form.BuyTicketForm;
import com.in6k.aviaTicketDesk.form.CreateFlightForm;
import com.in6k.aviaTicketDesk.service.AirportService;
import com.in6k.aviaTicketDesk.service.FlightService;
import com.in6k.aviaTicketDesk.validator.CreateFlightValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * Created by employee on 8/3/16.
 */
// TODO: 8/4/16 Подключить валидатор
@Controller
@RequestMapping
public class FlightController {
    @Autowired
    AirportService airportService;

    @Autowired
    FlightService flightService;

    @Autowired
    CreateFlightValidator createFlightValidator;

    @RequestMapping(value = "/createFlight", method = RequestMethod.GET)
    public String createFlightGet(ModelMap model) {


        model.put("departureAirports", airportService.getAll());
        // TODO: 8/3/16 Исключить один аеропорт (departureAirport)
        model.put("arrivalAirports", airportService.getAll());
        return "createFlight";
    }

    @RequestMapping(value = "/createFlight", method = RequestMethod.POST)
    public String createFlightPost(
            ModelMap model,
            @RequestParam("departureAirport") int departureAirportId,
            @RequestParam("arrivalAirport") int arrivalAirportId,
            @RequestParam("aircraftCapacity") int aircraftCapacity,
            @RequestParam("departureDateTime") String departureDateTime
    ) {

        Flight createdFlight = flightService.create(
                airportService.getById(departureAirportId),
                airportService.getById(arrivalAirportId),
                aircraftCapacity,
                LocalDateTime.parse(departureDateTime));
        model.put("result", createdFlight);
        model.put("departureAirports", airportService.getAll());
        model.put("arrivalAirports", airportService.getAll());
        return "createFlight";
    }
}
