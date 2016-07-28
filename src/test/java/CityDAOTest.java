import com.in6k.aviaTicketDesk.entity.City;
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
public class CityDAOTest extends DatabaseInitializer {
    private List<City> allCities;

    @Before
    public void setUp() throws Exception {
        prepareDatabase();
        allCities = asList(
                new City("Tripoli"),
                new City("Manacor"),
                new City("Andorra"),
                new City("Bremen"),
                new City("Motala"),
                new City("Elk"),
                new City("Edinburgh")
        );
    }

    @After
    public void tearDown() throws Exception {
        shutDownDatabase();
    }

    @Test
    public void create() throws Exception {
        assertThat(cityDAO.create("London"), is(new City("London")));
    }

    @Test
    public void getAll() throws Exception {
        assertThat(cityDAO.getAll(), is(allCities));
    }

    @Test
    public void createAndGetAll() throws Exception {
        List<City> expected = new ArrayList<>();
        expected.addAll(allCities);
        expected.add(new City("London"));
        cityDAO.create("London");
        assertThat(cityDAO.getAll(), is(expected));
    }

    @Test
    public void getById() throws Exception {
        assertThat(cityDAO.getCityById(0), is(new City("Tripoli")));
    }

    @Test
    public void createAndGetById() throws Exception {
        cityDAO.create("Aktau");
        assertThat(cityDAO.getCityById(7), is(new City("Aktau")));
    }

}
