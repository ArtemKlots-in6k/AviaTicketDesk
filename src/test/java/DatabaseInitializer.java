import com.in6k.aviaTicketDesk.dao.AirportDAO;
import com.in6k.aviaTicketDesk.dao.CityDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by employee on 7/28/16.
 */
public class DatabaseInitializer {
    private Statement statement;
    CityDAO cityDAO;
    AirportDAO airportDAO;


    void prepareDatabase() throws Exception {
        statement = DriverManager.getConnection("jdbc:hsqldb:mem:shop", "user", "852").createStatement();
        prepareTables();
        prepareCities();
        prepareAirports();
        beansSetUp();
    }

    private void prepareTables() throws SQLException {
        statement.executeUpdate("CREATE TABLE cities (" +
                        "id INTEGER IDENTITY PRIMARY KEY, " +
                        "title VARCHAR(50) );");

        statement.executeUpdate("CREATE TABLE airports (" +
                        "id INTEGER IDENTITY PRIMARY KEY, " +
                        "title VARCHAR(75), " +
                        "city_id INT NOT NULL )");

        statement.executeUpdate("ALTER TABLE airports " +
                "ADD FOREIGN KEY(city_id) " +
                "REFERENCES cities(id)");
    }

    private void beansSetUp() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"config.xml"});
        cityDAO = (CityDAO) applicationContext.getBean("cityDAO");
        airportDAO = (AirportDAO) applicationContext.getBean("airportDAO");
    }

    private void prepareCities() throws SQLException {
        statement.executeUpdate("INSERT INTO cities (title) VALUES ('Tripoli');");
        statement.executeUpdate("INSERT INTO cities (title) VALUES ( 'Manacor');");
        statement.executeUpdate("INSERT INTO cities (title) VALUES ( 'Andorra');");
        statement.executeUpdate("INSERT INTO cities (title) VALUES ( 'Bremen');");
        statement.executeUpdate("INSERT INTO cities (title) VALUES ( 'Motala');");
        statement.executeUpdate("INSERT INTO cities (title) VALUES ( 'Elk');");
        statement.executeUpdate("INSERT INTO cities (title) VALUES ( 'Edinburgh');");

    }

    private void prepareAirports() throws SQLException {
        statement.executeUpdate("INSERT INTO airports (title, city_id) VALUES ('Tripoli International Airport', 0);");
        statement.executeUpdate("INSERT INTO airports (title, city_id) VALUES ('Battle of Tripoli Airport', 0);");
        statement.executeUpdate("INSERT INTO airports (title, city_id) VALUES ('Palma Mallorca Airport', 1);");
        statement.executeUpdate("INSERT INTO airports (title, city_id) VALUES ('Aeroport d`Andorra la Seu', 2);");
        statement.executeUpdate("INSERT INTO airports (title, city_id) VALUES ('Bremen Airport', 3);");
        statement.executeUpdate("INSERT INTO airports (title, city_id) VALUES ('Stockholm-Arlanda Airport (ARN)', 4);");
        statement.executeUpdate("INSERT INTO airports (title, city_id) VALUES ('Elk City Regional Business Airport', 5);");
    }

    void shutDownDatabase() throws SQLException {
        statement.executeUpdate("DROP TABLE airports");
        statement.executeUpdate("DROP TABLE cities");
    }
}
