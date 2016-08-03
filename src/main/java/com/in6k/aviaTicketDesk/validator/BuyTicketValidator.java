package com.in6k.aviaTicketDesk.validator;

import com.in6k.aviaTicketDesk.dao.PassengerDAO;
import com.in6k.aviaTicketDesk.entity.Flight;
import com.in6k.aviaTicketDesk.form.BuyTicketForm;
import com.in6k.aviaTicketDesk.service.FlightService;
import com.in6k.aviaTicketDesk.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by employee on 8/3/16.
 */
@Component
public class BuyTicketValidator implements Validator {
    @Autowired
    PassengerService passengerService;

    @Autowired
    FlightService flightService;

    @Override
    public boolean supports(Class<?> clazz) {
        return BuyTicketForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BuyTicketForm buyTicketForm = (BuyTicketForm) target;
        Flight flight = flightService.getById(buyTicketForm.getFlight());

        ValidationUtils.rejectIfEmpty(errors, "passengerName", "passengerName.empty", "Name field must not be empty.");
        ValidationUtils.rejectIfEmpty(errors, "numberOfTickets", "numberOfTickets.empty", "Name field must not be empty.");
        ValidationUtils.rejectIfEmpty(errors, "flight", "flight.empty", "Name field must not be empty.");

        if (!passengerService.isThisPassengerExist(buyTicketForm.getPassengerName())) {
            errors.rejectValue("passengerName", "passengerName.UserDoesNotExist", "User does't Exist!");
        }

        if (buyTicketForm.getNumberOfTickets() < 1) {
            errors.rejectValue("numberOfTickets", "numberOfTickets.NumberMustBeMoreThanOne", "Number must be more that one");
        }

        if (buyTicketForm.getNumberOfTickets() > flight.getFreeSeats()) {
            errors.rejectValue("numberOfTickets", "numberOfTickets.noPlaces",
                    "Sorry, but we have just " + flight.getFreeSeats() + "seats");
        }
    }
}
