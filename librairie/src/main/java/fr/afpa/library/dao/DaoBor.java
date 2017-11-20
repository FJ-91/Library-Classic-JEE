package fr.afpa.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import fr.afpa.library.model.Borrowing;

public class DaoBor implements IDaoBor {

	@Override
	public Borrowing getBor(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			Borrowing b = null;
	    	session.beginTransaction();
			b = session.load(Borrowing.class, id);
			session.getTransaction().commit();
			
			return b;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Borrowing> getBors() {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			List<Borrowing> bors = new ArrayList<Borrowing>();
	    	session.beginTransaction();
	    	bors = session.createQuery("from Borrowing").list();
			session.getTransaction().commit();
			
			return bors;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteBor(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
	    	session.beginTransaction();
	    	Borrowing b = session.load(Borrowing.class, id);
			session.delete(b);
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
	public boolean addBor(Borrowing b) throws DateException {
		Session session = HibernateConnect.getSessionFactory().openSession();
		if (b.getBorDate().compareTo(b.getBorDateExpected()) > -1) {
			throw new DateException("The borrowing date must be before the expected return date !");
		} else {
			session.beginTransaction();
			session.save(b);
			session.getTransaction().commit();
			
			return true;
		}
		   	
	}

	@Override
	public boolean editBor(Borrowing b) throws DateException {
		Session session = HibernateConnect.getSessionFactory().openSession();
		if (b.getBorDate().compareTo(b.getBorDateExpected()) > -1) {
			throw new DateException("The borrowing date must be before the expected return date !");
		} else if (b.getBorDateReturn() != null && b.getBorDate().compareTo(b.getBorDateReturn()) > -1) {
			throw new DateException("The borrowing date must be before the return date !");
		} else {
			session.beginTransaction();
			session.update(b);
			session.getTransaction().commit();
			
			return true;
		}
	}
	
	@Override
	public boolean returnCopy(Borrowing b) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
	    	session.beginTransaction();
			session.update(b);
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
