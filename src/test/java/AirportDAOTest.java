import com.in6k.aviaTicketDesk.entity.Airport;
import com.in6k.aviaTicketDesk.entity.City;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by employee on 7/28/16.
 */
public class AirportDAOTest extends DatabaseInitializer{
    private List<Airport> allAllAirports;

    @Before
    public void setUp() throws Exception {
        prepareDatabase();
        allAllAirports  = asList(
                new Airport("Tripoli International Airport", cityDAO.getCityById(0)),
                new Airport("Battle of Tripoli Airport", cityDAO.getCityById(0)),
                new Airport("Palma Mallorca Airport", cityDAO.getCityById(1)),
                new Airport("Aeroport d`Andorra la Seu", cityDAO.getCityById(2)),
                new Airport("Bremen Airport", cityDAO.getCityById(3)),
                new Airport("Stockholm-Arlanda Airport (ARN)", cityDAO.getCityById(4)),
                new Airport("Elk City Regional Business Airport", cityDAO.getCityById(5))
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
        assertThat(airportDAO.getAll(), is(allAllAirports));
    }

    @Test
    public void createAndGetAll() throws Exception {
        City city = cityDAO.getCityById(6);
        List<Airport> expectedAirports = new ArrayList<>();
        expectedAirports.addAll(allAllAirports);
        expectedAirports.add(new Airport("Edinburgh Airport", city));

        airportDAO.create("Edinburgh Airport", city);

        assertThat(airportDAO.getAll(), is(expectedAirports));
    }

    @Test
    public void getAirportById() throws Exception {
        assertThat(cityDAO.getCityById(1), is(new Airport("Battle of Tripoli Airport", cityDAO.getCityById(0))));
    }

    @Test
    public void createAndGet() throws Exception {
        Airport newAirport = airportDAO.create("Battle of Tripoli Airport", cityDAO.getCityById(0));
        assertThat(airportDAO.getAirportById(7), is(newAirport));
    }
}
