package com.in6k.aviaTicketDesk;

import com.in6k.aviaTicketDesk.dao.AirportDAO;
import com.in6k.aviaTicketDesk.dao.CityDAO;
import com.in6k.aviaTicketDesk.dao.FlightDAO;
import com.in6k.aviaTicketDesk.dao.PassengerDAO;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by employee on 8/2/16.
 */
public class FakeData {
    private CityDAO cityDAO;
    private AirportDAO airportDAO;
    private FlightDAO flightDAO;
    private PassengerDAO passengerDAO;

    public FakeData() {

    }

    public void addDataToDatabase() {
        List<String> cities = asList("Tripoli", "Manacor", "Andorra", "Bremen", "Motala", "Elk", "Edinburgh");
        createCities(cities);
        createAirports();
        createFlights();
        createUsers();
    }

    private void createUsers() {
        List<String> passengers = asList("John", "Bob", "Andre", "Jenifer", "Marta", "Lola", "Fill");
        for (String passenger : passengers) {
            passengerDAO.create(passenger);
        }
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

    public void setCityDAO(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public CityDAO getCityDAO() {
        return cityDAO;
    }

    public void setAirportDAO(AirportDAO airportDAO) {
        this.airportDAO = airportDAO;
    }

    public AirportDAO getAirportDAO() {
        return airportDAO;
    }

    public void setFlightDAO(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    public FlightDAO getFlightDAO() {
        return flightDAO;
    }

    public void setPassengerDAO(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    public PassengerDAO getPassengerDAO() {
        return passengerDAO;
    }
}
