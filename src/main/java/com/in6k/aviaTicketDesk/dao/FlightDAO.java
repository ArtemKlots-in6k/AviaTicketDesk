package com.in6k.aviaTicketDesk.dao;

import com.in6k.aviaTicketDesk.entity.Airport;
import com.in6k.aviaTicketDesk.entity.Flight;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by employee on 7/28/16.
 */
@Transactional
public class FlightDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Flight create(Airport departureAirport,
                         Airport destinationAirport,
                         int aircraftCapacity,
                         LocalDateTime departureDateTime) throws Exception {

        Flight flight = new Flight(departureAirport,
                destinationAirport,
                aircraftCapacity,
                departureDateTime);

        getSession().save(flight);
        getSession().flush();
        return flight;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public List getAll() {
        return getSession().createCriteria(Flight.class).list();
    }

    public Flight getFlightById(int id) throws SQLException {
        Criteria flightCriteria = getSession().createCriteria(Flight.class);
        flightCriteria.add(Restrictions.eq("id", id));
        return (Flight) flightCriteria.uniqueResult();
    }

    public int getFreeSeatsByFlight(Flight flight) {
        Query query = sessionFactory.getCurrentSession().createQuery("" +
                "SELECT flights.freeSeats " +
                "FROM Flight flights " +
                "WHERE flights.id = :flightId "
        ).setParameter("flightId", flight.getId());
        return (int) query.list().get(0);
    }

    public void reserveSeats(Flight flight, int numberOfSeats) throws Exception {
        if (flight.getFreeSeats() - numberOfSeats < 0)
            throw new Exception("Not enough seats! ");

        Query query = getSession().createQuery("" +
                "UPDATE Flight SET freeSeats = :freeSeats " +
                "WHERE id = :flightId "
        )
                .setParameter("freeSeats", flight.getFreeSeats() - numberOfSeats)
                .setParameter("flightId", flight.getId());
        query.executeUpdate();
    }

    public Flight selectNextFlightToThisCityForFewPeople(Airport departureAirport, Airport destinationAirport, LocalDateTime today, int numberOfPersons) {
        Query query = sessionFactory.getCurrentSession().createQuery("" +
                "FROM Flight flight " +
                "WHERE departureAirport = :departureAirport " +
                "AND destinationAirport = :destinationAirport " +
                "AND freeSeats >= :numberOfPersons " +
                "AND departureDateTime > :today " +
                "ORDER BY departureDateTime ASC "
        )
                .setParameter("destinationAirport", destinationAirport)
                .setParameter("departureAirport", departureAirport)
                .setParameter("numberOfPersons", numberOfPersons)
                .setParameter("today", Timestamp.valueOf(today))
                .setMaxResults(1);

        return (Flight) query.list().get(0);
    }
}
