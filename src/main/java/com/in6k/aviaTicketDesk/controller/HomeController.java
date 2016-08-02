package com.in6k.aviaTicketDesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by employee on 8/2/16.
 */
@Controller
@RequestMapping
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome() {

        return "index";
    }

}
