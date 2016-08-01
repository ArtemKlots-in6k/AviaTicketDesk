package com.in6k.aviaTicketDesk.servlets;

import com.in6k.aviaTicketDesk.dao.AirportDAO;
import com.in6k.aviaTicketDesk.dao.CityDAO;
import com.in6k.aviaTicketDesk.entity.City;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by employee on 8/1/16.
 */
public class Cities extends ApplicationController {

//    @Autowired
//    private AviaTicketDesk aviaTicketDesk;

    @Autowired
    private CityDAO cityDAO;


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        cityDAO = ApplicationContext.getBean(CityDAO.class);

        cityDAO.create("123");

        request.setAttribute("cities", cityDAO.getAll());

        request.getRequestDispatcher("cities.jsp").forward(request, response);
    }

    public void destroy() {

    }
}
