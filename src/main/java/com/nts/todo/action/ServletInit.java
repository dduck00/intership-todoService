package com.nts.todo.action;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletInit implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Jdbc diver is no Exists.", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}
