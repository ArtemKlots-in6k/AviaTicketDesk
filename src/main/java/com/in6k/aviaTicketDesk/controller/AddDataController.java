package com.in6k.aviaTicketDesk.controller;

import com.in6k.aviaTicketDesk.dao.AirportDAO;
import com.in6k.aviaTicketDesk.dao.CityDAO;
import com.in6k.aviaTicketDesk.dao.FlightDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by employee on 8/2/16.
 */
public class AddDataController extends ApplicationController {
    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private AirportDAO airportDAO;

    @Autowired
    private FlightDAO flightDAO;


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cityDAO = ApplicationContext.getBean(CityDAO.class);
        airportDAO = ApplicationContext.getBean(AirportDAO.class);
        flightDAO = ApplicationContext.getBean(FlightDAO.class);

        fillDatabase();
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "/");

        request.getRequestDispatcher("jsp/flights.jsp").forward(request, response);
    }

    private void fillDatabase() {
        List<String> cities = asList("Tripoli", "Manacor", "Andorra", "Bremen", "Motala", "Elk", "Edinburgh");
        createCities(cities);
        createAirports();
        createFlights();
    }

    private void createCities(List<String> cities) {
        for (String city : cities) {
            cityDAO.create(city);
        }
    }

    private void createAirports() {
        airportDAO.create("Tripoli International Airport", cityDAO.getCityById(0));
        airportDAO.create("Elk City Regional Business Airport", cityDAO.getCityById(5));
        airportDAO.create("Aeroport d`Andorra la Seu", cityDAO.getCityById(2));
    }

    private void createFlights() {
        flightDAO.create(airportDAO.getAirportById(1), airportDAO.getAirportById(2), 150, LocalDateTime.of(2016, 7, 30, 20, 50));
        flightDAO.create(airportDAO.getAirportById(2), airportDAO.getAirportById(3), 300, LocalDateTime.of(2016, 7, 27, 17, 30));
        flightDAO.create(airportDAO.getAirportById(1), airportDAO.getAirportById(2), 150, LocalDateTime.of(2016, 8, 3, 5, 0));
    }

    public void destroy() {

    }
}
