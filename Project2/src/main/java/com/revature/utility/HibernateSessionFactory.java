package com.revature.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {	
	
	/*
	 * This is a utility class for building a SessionFactory and returning
	 * Hibernate Sessions. Note that our SessionFactory will be a singleton
	 * as SessionFactory(ies) are expensive to build and we don't need more
	 * than one SessionFactory to retrieve a large number of sessions.
	 */
	
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		ReadPropertyFile rpf = new ReadPropertyFile();
		
		if(sessionFactory == null) {
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", rpf.getURL()) 
					.setProperty("hibernate.connection.username", rpf.getUsername())
					.setProperty("hibernate.connection.password", rpf.getPassword())
					.buildSessionFactory();
		}
		
		return sessionFactory.getCurrentSession();
	}
	
	public static void main(String...args) {
		
	}
}
