package com.in6k.aviaTicketDesk.dao;

import com.in6k.aviaTicketDesk.entity.Airport;
import com.in6k.aviaTicketDesk.entity.City;
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
public class AirportDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Airport create(String title, City city) throws Exception {
        Airport airport = new Airport(title, city);
        getSession().save(airport);
        getSession().flush();
        return airport;
    }

    public List getAll() {
        return getSession().createCriteria(Airport.class).list();
    }

    public Airport getAirportById(int id) throws SQLException {
        Criteria airportCriteria = getSession().createCriteria(Airport.class);
        airportCriteria.add(Restrictions.eq("id", id));
        return (Airport) airportCriteria.uniqueResult();
    }

}
