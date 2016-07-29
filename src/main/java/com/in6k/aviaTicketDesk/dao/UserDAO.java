package com.in6k.aviaTicketDesk.dao;

import com.in6k.aviaTicketDesk.entity.City;
import com.in6k.aviaTicketDesk.entity.User;
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
public class UserDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public User create(String name) throws Exception {
        User user = new User(name);
        getSession().save(user);
        getSession().flush();
        return user;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public List getAll() {
        return getSession().createCriteria(User.class).list();
    }

    public User getUserById(int id) throws SQLException {
        Criteria userCriteria = getSession().createCriteria(User.class);
        userCriteria.add(Restrictions.eq("id", id));
        return (User) userCriteria.uniqueResult();
    }
}
