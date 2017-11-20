package fr.afpa.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import fr.afpa.library.model.Subscriber;

public class DaoSub implements IDaoSub{

	@Override
	public Subscriber getSub(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			Subscriber s = null;
	    	session.beginTransaction();
			s = session.load(Subscriber.class, id);
			session.getTransaction().commit();
			
			return s;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subscriber> getSubs() {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			List<Subscriber> subs = new ArrayList<Subscriber>();
	    	session.beginTransaction();
			subs = session.createQuery("from Subscriber").list();
			session.getTransaction().commit();
			
			return subs;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteSub(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			Subscriber s = null;
	    	session.beginTransaction();
			s = session.load(Subscriber.class, id);
			session.delete(s);
			session.getTransaction().commit();
			
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean addSub(Subscriber s) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
	    	session.beginTransaction();
			session.save(s);
			session.getTransaction().commit();
			
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean editSub(Subscriber s) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
	    	session.beginTransaction();
			session.update(s);
			session.getTransaction().commit();
			
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

}
