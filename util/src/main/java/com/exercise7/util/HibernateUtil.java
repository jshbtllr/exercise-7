package com.exercise7.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class HibernateUtil {

	private static SessionFactory sessionFactory;
	//private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
		if(sessionFactory == null) {
			AnnotationConfiguration configuration = new AnnotationConfiguration();
			sessionFactory = configuration.configure().buildSessionFactory();
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