package com.in6k.aviaTicketDesk.validator;

import com.in6k.aviaTicketDesk.entity.Ticket;
import com.in6k.aviaTicketDesk.form.BuyTicketForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by employee on 8/3/16.
 */
@Component
public class BuyTicketValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BuyTicketForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "passengerName", "passengerName.empty", "Name field must not be empty.");
        ValidationUtils.rejectIfEmpty(errors, "numberOfTickets", "numberOfTickets.empty", "Name field must not be empty.");
        ValidationUtils.rejectIfEmpty(errors, "flight", "flight.empty", "Name field must not be empty.");
    }
}
