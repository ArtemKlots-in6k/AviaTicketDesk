import com.in6k.aviaTicketDesk.entity.Flight;
import com.in6k.aviaTicketDesk.entity.Ticket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by employee on 7/29/16.
 */
public class AviaTicketDeskTest extends DatabaseInitializer {
    private List<Ticket> allTickets;

    @Before
    public void setUp() throws Exception {
        prepareDatabase();
        allTickets = asList(
                new Ticket(flightDAO.getFlightById(0), passengerDAO.getUserById(0)),
                new Ticket(flightDAO.getFlightById(2), passengerDAO.getUserById(3)),
                new Ticket(flightDAO.getFlightById(3), passengerDAO.getUserById(1)),
                new Ticket(flightDAO.getFlightById(3), passengerDAO.getUserById(2))
        );
    }

    @After
    public void tearDown() throws Exception {
        shutDownDatabase();
    }

    @Test
    public void buyTickets() throws Exception {
        int flightId = 0;
        int userId = 1;
        List<Ticket> addedTickets = asList(
                new Ticket(flightDAO.getFlightById(flightId), passengerDAO.getUserById(userId)),
                new Ticket(flightDAO.getFlightById(flightId), passengerDAO.getUserById(userId)),
                new Ticket(flightDAO.getFlightById(flightId), passengerDAO.getUserById(userId)),
                new Ticket(flightDAO.getFlightById(flightId), passengerDAO.getUserById(userId)),
                new Ticket(flightDAO.getFlightById(flightId), passengerDAO.getUserById(userId))
        );
        List<Ticket> allNewTickets = new ArrayList<>();
        allNewTickets.addAll(allTickets);
        allNewTickets.addAll(addedTickets);

        aviaTicketDesk.buyTickets(flightDAO.getFlightById(flightId), passengerDAO.getUserById(userId), 5);

        assertThat(ticketDAO.getAll(), is(allNewTickets));
    }

    @Test
    public void getAllFlights() throws Exception {
        List<Flight> allFlights = asList(
                new Flight(airportDAO.getAirportById(1), airportDAO.getAirportById(5), 120, LocalDateTime.of(2016, 7, 25, 16, 20)),
                new Flight(airportDAO.getAirportById(3), airportDAO.getAirportById(5), 300, LocalDateTime.of(2016, 8, 10, 17, 20)),
                new Flight(airportDAO.getAirportById(4), airportDAO.getAirportById(1), 200, LocalDateTime.of(2016, 7, 15, 13, 5)),
                new Flight(airportDAO.getAirportById(2), airportDAO.getAirportById(3), 100, LocalDateTime.of(2016, 7, 13, 14, 5))
        );
        assertThat(flightDAO.getAll(), is(allFlights));
    }
}
