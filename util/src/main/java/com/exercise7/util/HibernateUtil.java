package com.exercise7.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	//private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
		if(sessionFactory == null) {
			Configuration configuration = new Configuration();
		//	serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.configure("com/exercise7/infra/persistence/hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		return buildSessionFactory();
	}

	public static void shutdown() {
		sessionFactory.close();
	}

}