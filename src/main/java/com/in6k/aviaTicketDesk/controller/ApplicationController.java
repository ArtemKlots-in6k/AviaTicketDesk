package com.in6k.aviaTicketDesk.controller;

import javax.servlet.http.HttpServlet;

/**
 * Created by employee on 8/1/16.
 */
public abstract class ApplicationController extends HttpServlet {

    @Override
    public void init() {
        ApplicationContext.setupBeans(this);
    }
}
