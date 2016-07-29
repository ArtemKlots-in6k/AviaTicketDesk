import com.in6k.aviaTicketDesk.dao.AirportDAO;
import com.in6k.aviaTicketDesk.dao.CityDAO;
import com.in6k.aviaTicketDesk.dao.FlightDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    FlightDAO flightDAO;


    void prepareDatabase() throws Exception {
        statement = DriverManager.getConnection("jdbc:hsqldb:mem:shop", "user", "852").createStatement();
        prepareTables();
        prepareCities();
        prepareAirports();
        prepareFlights();
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

        statement.executeUpdate("CREATE TABLE flights (" +
                "id INTEGER IDENTITY PRIMARY KEY, " +
                "departure_airport_id INT NOT NULL, " +
                "destination_airport_id INT NOT NULL, " +
                "aircraft_capacity INT NOT NULL, " +
                "free_seats INT NOT NULL, " +
                "departure_date_time DATETIME )");

        statement.executeUpdate("ALTER TABLE flights " +
                "ADD FOREIGN KEY(departure_airport_id) " +
                "REFERENCES airports(id)");

        statement.executeUpdate("ALTER TABLE flights " +
                "ADD FOREIGN KEY(destination_airport_id) " +
                "REFERENCES airports(id)");
    }

    private void beansSetUp() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"config.xml"});
        cityDAO = (CityDAO) applicationContext.getBean("cityDAO");
        airportDAO = (AirportDAO) applicationContext.getBean("airportDAO");
        flightDAO = (FlightDAO) applicationContext.getBean("flightDAO");
    }

    private void prepareCities() throws SQLException {
        addCity("Tripoli");
        addCity("Manacor");
        addCity("Andorra");
        addCity("Bremen");
        addCity("Motala");
        addCity("Elk");
        addCity("Edinburgh");
    }

    private void addCity(String title) throws SQLException {
        statement.executeUpdate("INSERT INTO cities (title) VALUES ('" + title + "');");
    }

    private void prepareAirports() throws SQLException {
        addAirport("Tripoli International Airport", 0);
        addAirport("Battle of Tripoli Airport", 0);
        addAirport("Palma Mallorca Airport", 1);
        addAirport("Aeroport d`Andorra la Seu", 2);
        addAirport("Bremen Airport", 3);
        addAirport("Stockholm-Arlanda Airport (ARN)", 4);
        addAirport("Elk City Regional Business Airport", 5);
    }

    private void addAirport(String title, int cityId) throws SQLException {
        statement.executeUpdate("INSERT INTO airports (title, city_id) VALUES ('" + title + "', " + cityId + ");");
    }

    private void prepareFlights() throws SQLException {
        addFlight(1, 5, 120, 120, "2016-07-25 16:20:00");
        addFlight(3, 5, 300, 300, "2016-08-10 17:20:00");
        addFlight(4, 1, 200, 200, "2016-07-15 13:05:00");
        addFlight(2, 3, 100, 100, "2016-07-13 14:05:00");
    }

    private void addFlight(int departureAirportId,
                           int destinationAirportId,
                           int aircraftCapacity,
                           int freeSeats,
                           String departureDateTime) throws SQLException {
        statement.executeUpdate("" +
                "INSERT INTO flights (" +
                "departure_airport_id, " +
                "destination_airport_id, " +
                "aircraft_capacity, " +
                "free_seats, " +
                "departure_date_time ) " +
                "VALUES (" +
                departureAirportId + "," +
                destinationAirportId + "," +
                aircraftCapacity + "," +
                freeSeats + "," +
                "'" + departureDateTime + "');");
    }

    void shutDownDatabase() throws SQLException {
        statement.executeUpdate("DROP TABLE flights");
        statement.executeUpdate("DROP TABLE airports");
        statement.executeUpdate("DROP TABLE cities");
    }
}
