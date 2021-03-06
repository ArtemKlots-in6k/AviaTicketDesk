package com.in6k.aviaTicketDesk.dao;

import com.in6k.aviaTicketDesk.entity.Passenger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by employee on 7/28/16.
 */
@Transactional
public class PassengerDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Passenger create(String name) throws Exception {
        Passenger passenger = new Passenger(name);
        getSession().save(passenger);
        getSession().flush();
        return passenger;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public List getAll() {
        return getSession().createCriteria(Passenger.class).list();
    }

    public Passenger getUserById(int id) throws SQLException {
        Criteria passengerCriteria = getSession().createCriteria(Passenger.class);
        passengerCriteria.add(Restrictions.eq("id", id));
        return (Passenger) passengerCriteria.uniqueResult();
    }
}
