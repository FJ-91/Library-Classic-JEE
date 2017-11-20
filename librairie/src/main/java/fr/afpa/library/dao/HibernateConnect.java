package fr.afpa.library.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import fr.afpa.library.model.*;


public class HibernateConnect {
	
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration cfg = new Configuration()
					.configure()
					.addAnnotatedClass(Author.class)
					.addAnnotatedClass(Book.class)
					.addAnnotatedClass(Catalogue.class)
					.addAnnotatedClass(Copy.class)
					.addAnnotatedClass(Subscriber.class)
					.addAnnotatedClass(Borrowing.class);
	    	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	    	sessionFactory = cfg.buildSessionFactory(reg);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
