import com.in6k.aviaTicketDesk.entity.Passenger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by employee on 8/3/16.
 */
public class PassengerServiceTest extends DatabaseInitializer {

    @Before
    public void setUp() throws Exception {
        prepareDatabase();
    }

    @After
    public void tearDown() throws Exception {
        shutDownDatabase();
    }

    @Test
    public void isPassengerExist() throws Exception {
        assertThat(passengerService.isThisPassengerExist("John"), is(true));
    }

    @Test
    public void isAnotherPassengerExist() throws Exception {
        assertThat(passengerService.isThisPassengerExist("Bob"), is(true));
    }

    @Test
    public void isPassengerDoesntExist() throws Exception {
        assertThat(passengerService.isThisPassengerExist("invalidData"), is(false));
    }
}
