import com.in6k.aviaTicketDesk.entity.Airport;
import com.in6k.aviaTicketDesk.entity.City;
import com.in6k.aviaTicketDesk.entity.Flight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by employee on 7/28/16.
 */
public class FlightDAOTest extends DatabaseInitializer {
    private List<Flight> allFlights;

    @Before
    public void setUp() throws Exception {
        prepareDatabase();
        allFlights = asList(
                new Flight(airportDAO.getAirportById(1), airportDAO.getAirportById(5), 120, LocalDateTime.of(2016, 7, 25, 16, 20)),
                new Flight(airportDAO.getAirportById(3), airportDAO.getAirportById(5), 300, LocalDateTime.of(2016, 8, 10, 17, 20)),
                new Flight(airportDAO.getAirportById(4), airportDAO.getAirportById(1), 200, LocalDateTime.of(2016, 7, 15, 13, 5)),
                new Flight(airportDAO.getAirportById(2), airportDAO.getAirportById(3), 100, LocalDateTime.of(2016, 7, 13, 14, 5))
        );
    }

    @After
    public void tearDown() throws Exception {
        shutDownDatabase();
    }

    @Test
    public void getById() throws Exception {
        Flight expectedFlight = new Flight(
                airportDAO.getAirportById(1),
                airportDAO.getAirportById(5),
                120,
                LocalDateTime.of(2016, 7, 25, 16, 20));


        Flight flight = flightDAO.getFlightById(0);

        assertThat(flight, is(expectedFlight));
    }

    @Test
    public void addAndGetById() throws Exception {
        Flight expectedFlight = new Flight(
                airportDAO.getAirportById(1),
                airportDAO.getAirportById(5),
                120,
                LocalDateTime.of(2016, 7, 25, 16, 20));

        flightDAO.create(
                airportDAO.getAirportById(1),
                airportDAO.getAirportById(5),
                120,
                LocalDateTime.of(2016, 7, 25, 16, 20)
        );

        assertThat(flightDAO.getFlightById(4), is(expectedFlight));
    }

    @Test
    public void getAll() throws Exception {
        assertThat(flightDAO.getAll(), is(allFlights));
    }

    @Test
    public void getFreeSeats() throws Exception {
        assertThat(flightDAO.getFreeSeatsByFlight(flightDAO.getFlightById(0)), is(120));
    }

    @Test
    public void getFreeSeatsForAnotherFlight() throws Exception {
        assertThat(flightDAO.getFreeSeatsByFlight(flightDAO.getFlightById(1)), is(300));
    }

    @Test
    public void reserveOneHundredSeat() throws Exception {
        Flight flight = flightDAO.getFlightById(0);

        flightDAO.reserveSeats(flight, 100);

        assertThat(flightDAO.getFlightById(0).getFreeSeats(), is(20));
    }

    @Test
    public void reserveTwentySeat() throws Exception {
        Flight flight = flightDAO.getFlightById(0);

        flightDAO.reserveSeats(flight, 20);

        assertThat(flightDAO.getFlightById(0).getFreeSeats(), is(100));
    }

    @Test(expected = Exception.class)
    public void reserveMoreThanMaximumSeats() throws Exception {
        Flight flight = flightDAO.getFlightById(0);

        flightDAO.reserveSeats(flight, 999999);
    }

    @Test
    public void selectNextFlightToThisCityForFewPeople() throws Exception {
        Flight result = flightDAO.selectNextFlightToThisCityForFewPeople(
                airportDAO.getAirportById(1),
                airportDAO.getAirportById(5),
                LocalDateTime.of(2016, 7, 23, 20, 0),
                5);

        assertThat(result, is(new Flight(
                airportDAO.getAirportById(1),
                airportDAO.getAirportById(5),
                120,
                LocalDateTime.of(2016, 7, 25, 16, 20))));
    }

    @Test(expected = Exception.class)
    public void selectNextFlightToThisCityForLotsOfPeople() throws Exception {
        flightDAO.selectNextFlightToThisCityForFewPeople(
                airportDAO.getAirportById(1),
                airportDAO.getAirportById(5),
                LocalDateTime.of(2016, 7, 23, 20, 0),
                99999);
    }

    @Test
    public void selectAnotherNextFlightToThisCityForFewPeople() throws Exception {
        prepareFlights();
        Flight result = flightDAO.selectNextFlightToThisCityForFewPeople(
                airportDAO.getAirportById(1),
                airportDAO.getAirportById(2),
                LocalDateTime.of(2016, 7, 23, 20, 0),
                5);

        assertThat(result, is(new Flight(
                airportDAO.getAirportById(1),
                airportDAO.getAirportById(2),
                120,
                LocalDateTime.of(2016, 7, 25, 16, 20))));
    }

    private void prepareFlights() throws Exception {
        flightDAO.create(
                airportDAO.getAirportById(1),
                airportDAO.getAirportById(2),
                120,
                LocalDateTime.of(2016, 7, 25, 16, 30));

        flightDAO.create(
                airportDAO.getAirportById(1),
                airportDAO.getAirportById(2),
                120,
                LocalDateTime.of(2016, 7, 25, 16, 20));
    }
}
