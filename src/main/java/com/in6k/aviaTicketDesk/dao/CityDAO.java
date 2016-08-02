package com.in6k.aviaTicketDesk.dao;

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
public class CityDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public City create(String title) {
        City city = new City(title);
        getSession().save(city);
        getSession().flush();
        return city;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public List getAll() {
        return getSession().createCriteria(City.class).list();
    }

    public City getCityById(int id) {
        Criteria cityCriteria = getSession().createCriteria(City.class);
        cityCriteria.add(Restrictions.eq("id", id));
        return (City) cityCriteria.uniqueResult();
    }
}
