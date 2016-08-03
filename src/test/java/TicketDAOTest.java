import com.in6k.aviaTicketDesk.entity.Ticket;
import com.in6k.aviaTicketDesk.entity.Passenger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by employee on 7/29/16.
 */
public class TicketDAOTest extends DatabaseInitializer {
    private List<Ticket> allTickets;

    @Before
    public void setUp() throws Exception {
        prepareDatabase();
        allTickets = asList(
                new Ticket(flightDAO.getFlightById(0), passengerDAO.getById(0)),
                new Ticket(flightDAO.getFlightById(2), passengerDAO.getById(3)),
                new Ticket(flightDAO.getFlightById(3), passengerDAO.getById(1)),
                new Ticket(flightDAO.getFlightById(3), passengerDAO.getById(2))
        );
    }

    @After
    public void tearDown() throws Exception {
        shutDownDatabase();
    }

    @Test
    public void create() throws Exception {
        assertThat(ticketDAO.create(flightDAO.getFlightById(0), passengerDAO.getById(0)),
                is(new Ticket(flightDAO.getFlightById(0), new Passenger("John"))));
    }

    @Test
    public void getAll() throws Exception {
        assertThat(ticketDAO.getAll(), is(allTickets));
    }

    @Test
    public void createAndGetAll() throws Exception {
        List<Ticket> expected = new ArrayList<>();
        expected.addAll(allTickets);
        expected.add(new Ticket(flightDAO.getFlightById(0), passengerDAO.getById(1)));

        ticketDAO.create(flightDAO.getFlightById(0), passengerDAO.getById(1));

        assertThat(ticketDAO.getAll(), is(expected));
    }

    @Test
    public void getById() throws Exception {
        assertThat(ticketDAO.getTicketById(0),
                is(new Ticket(flightDAO.getFlightById(0), passengerDAO.getById(0))));
    }

    @Test
    public void createAndGetById() throws Exception {
        ticketDAO.create(flightDAO.getFlightById(2), passengerDAO.getById(1));

        assertThat(ticketDAO.getTicketById(4),
                is(new Ticket(flightDAO.getFlightById(2), passengerDAO.getById(1))));
    }
}
