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
 * Created by employee on 7/28/16.
 */
public class PassengerDAOTest extends DatabaseInitializer {
    private List<Passenger> allPassengers;

    @Before
    public void setUp() throws Exception {
        prepareDatabase();
        allPassengers = asList(
                new Passenger("John"),
                new Passenger("Bob"),
                new Passenger("Andre"),
                new Passenger("Jenifer"),
                new Passenger("Marta"),
                new Passenger("Lola"),
                new Passenger("Fill")
        );
    }

    @After
    public void tearDown() throws Exception {
        shutDownDatabase();
    }

    @Test
    public void create() throws Exception {
        assertThat(passengerDAO.create("Tom"), is(new Passenger("Tom")));
    }

    @Test
    public void getAll() throws Exception {
        assertThat(passengerDAO.getAll(), is(allPassengers));
    }

    @Test
    public void createAndGetAll() throws Exception {
        List<Passenger> expected = new ArrayList<>();
        expected.addAll(allPassengers);
        expected.add(new Passenger("Tom"));
        passengerDAO.create("Tom");
        assertThat(passengerDAO.getAll(), is(expected));
    }

    @Test
    public void getById() throws Exception {
        assertThat(passengerDAO.getById(0), is(new Passenger("John")));
    }

    @Test
    public void createAndGetById() throws Exception {
        passengerDAO.create("Rose");
        assertThat(passengerDAO.getById(7), is(new Passenger("Rose")));
    }

    @Test
    public void getPassengerByName() throws Exception {
        assertThat(passengerDAO.getByName("John"), is(new Passenger("John")));
    }

    @Test
    public void getPassengerByAnotherName() throws Exception {
        assertThat(passengerDAO.getByName("Bob"), is(new Passenger("Bob")));
    }
}
