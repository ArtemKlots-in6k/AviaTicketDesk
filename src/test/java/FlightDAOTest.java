import com.in6k.aviaTicketDesk.entity.Airport;
import com.in6k.aviaTicketDesk.entity.City;
import com.in6k.aviaTicketDesk.entity.Flight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
}
