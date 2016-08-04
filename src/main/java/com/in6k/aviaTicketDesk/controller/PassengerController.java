package com.in6k.aviaTicketDesk.controller;

import com.in6k.aviaTicketDesk.entity.Passenger;
import com.in6k.aviaTicketDesk.form.RegisterPassengerForm;
import com.in6k.aviaTicketDesk.service.PassengerService;
import com.in6k.aviaTicketDesk.validator.RegisterPassengerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by employee on 8/4/16.
 */
@Controller
@RequestMapping
public class PassengerController {
    @Autowired
    PassengerService passengerService;

    @Autowired
    RegisterPassengerValidator registerPassengerValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPassengerGet(ModelMap model) {
        RegisterPassengerForm registerPassengerForm = new RegisterPassengerForm();

        model.put("registerPassengerForm", registerPassengerForm);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPassengerPost(
            ModelMap model,
            RegisterPassengerForm registerPassengerForm,
            BindingResult result
    ) {
        registerPassengerValidator.validate(registerPassengerForm, result);
        if (result.hasErrors()) {
            return "register";
        }

        Passenger createdPassenger = passengerService.create(registerPassengerForm.getName());
        model.put("createdPassengerName", createdPassenger.getName());
        return "register";
    }
}
