package com.in6k.aviaTicketDesk.validator;

import com.in6k.aviaTicketDesk.form.RegisterPassengerForm;
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
public class RegisterPassengerValidator implements Validator {
    @Autowired
    PassengerService passengerService;

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterPassengerForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterPassengerForm registerPassengerForm = (RegisterPassengerForm) target;

        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty", "Name field must not be empty.");

        if (passengerService.isThisPassengerExist(registerPassengerForm.getName())) {
            errors.rejectValue("name", "name.UserAlreadyExist", "Sorry, but this user already registered");
        }
    }
}
