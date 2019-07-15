package com.nts.todo.action;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nts.todo.dao.TodoDao;

public class DaoInit implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		TodoDao dbConnector = new TodoDao();
		servletContext.setAttribute("DB_CONNECTOR", dbConnector);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.removeAttribute("DB_CONNECTOR");
	}

}
