package com.in6k.aviaTicketDesk.servlets;

import com.in6k.aviaTicketDesk.dao.AirportDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by employee on 8/1/16.
 */
public class ApplicationContext implements ServletContextListener {
    private static ClassPathXmlApplicationContext applicationContext;

    public ClassPathXmlApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setupBeans(Object o) {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(o);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"config.xml"});
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }
}
