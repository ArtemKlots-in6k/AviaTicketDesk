import com.in6k.aviaTicketDesk.entity.Airport;
import com.in6k.aviaTicketDesk.entity.City;
import com.in6k.aviaTicketDesk.entity.Flight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

        );
    }

    @After
    public void tearDown() throws Exception {
        shutDownDatabase();
    }

    @Test
    public void create() throws Exception {
        City city = cityDAO.getCityById(6);
        assertThat(airportDAO.create("Edinburgh Airport", city), is(new Airport("Edinburgh Airport", city)));
    }

    @Test
    public void getAll() throws Exception {
//        assertThat(.getAll(), is(allFlights));
    }
}
