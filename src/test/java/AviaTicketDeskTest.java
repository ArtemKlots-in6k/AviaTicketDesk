import com.in6k.aviaTicketDesk.entity.Ticket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

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
        List<Ticket> addedTickets = asList(
                new Ticket(flightDAO.getFlightById(0), passengerDAO.getUserById(1)),
                new Ticket(flightDAO.getFlightById(0), passengerDAO.getUserById(1)),
                new Ticket(flightDAO.getFlightById(0), passengerDAO.getUserById(1)),
                new Ticket(flightDAO.getFlightById(0), passengerDAO.getUserById(1)),
                new Ticket(flightDAO.getFlightById(0), passengerDAO.getUserById(1))
        );
        List<Ticket> allNewTickets = new ArrayList<>();
        allNewTickets.addAll(allTickets);
        allNewTickets.addAll(addedTickets);

        aviaTicketDesk.buyTickets(flightDAO.getFlightById(0), passengerDAO.getUserById(1), 5);

        assertThat(ticketDAO.getAll(), is(allNewTickets));
    }
}
