import com.in6k.aviaTicketDesk.entity.User;
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
public class UserDAOTest extends DatabaseInitializer {
    private List<User> allusers;

    @Before
    public void setUp() throws Exception {
        prepareDatabase();
        allusers = asList(
                new User("John"),
                new User("Bob"),
                new User("Andre"),
                new User("Jenifer"),
                new User("Marta"),
                new User("Lola"),
                new User("Fill")
        );
    }

    @After
    public void tearDown() throws Exception {
        shutDownDatabase();
    }

    @Test
    public void create() throws Exception {
        assertThat(userDAO.create("Tom"), is(new User("Tom")));
    }

    @Test
    public void getAll() throws Exception {
        assertThat(userDAO.getAll(), is(allusers));
    }

    @Test
    public void createAndGetAll() throws Exception {
        List<User> expected = new ArrayList<>();
        expected.addAll(allusers);
        expected.add(new User("Tom"));
        userDAO.create("Tom");
        assertThat(userDAO.getAll(), is(expected));
    }

    @Test
    public void getById() throws Exception {
        assertThat(userDAO.getUserById(0), is(new User("John")));
    }

    @Test
    public void createAndGetById() throws Exception {
        userDAO.create("Rose");
        assertThat(userDAO.getUserById(7), is(new User("Rose")));
    }

}
