package com.in6k.aviaTicketDesk.dao;

import com.in6k.aviaTicketDesk.entity.Flight;
import com.in6k.aviaTicketDesk.entity.Ticket;
import com.in6k.aviaTicketDesk.entity.Passenger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 7/29/16.
 */
@Transactional
public class TicketDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Ticket create(Flight flight, Passenger passenger) throws Exception {
        Ticket ticket = new Ticket(flight, passenger);
        getSession().save(ticket);
        getSession().flush();
        return ticket;
    }

    public List<Ticket> getAll() {
        return getSession().createCriteria(Ticket.class).list();
    }


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Ticket getTicketById(int id) throws SQLException {
        Criteria ticketCriteria = getSession().createCriteria(Ticket.class);
        ticketCriteria.add(Restrictions.eq("id", id));
        return (Ticket) ticketCriteria.uniqueResult();
    }
}
