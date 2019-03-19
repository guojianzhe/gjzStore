package com.heima.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static Configuration configuration = null;
	private static SessionFactory sessionFactory = null;
	static {
		configuration = new Configuration();
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();
	}
	
	public static Session openSession() {
		
		return sessionFactory.openSession();
	}
	
	public static Session getCurrentSession() {
		
		return sessionFactory.getCurrentSession();
	}
	
}
