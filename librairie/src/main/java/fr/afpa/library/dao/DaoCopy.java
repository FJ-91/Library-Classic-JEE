package fr.afpa.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import fr.afpa.library.model.Copy;

public class DaoCopy implements IDaoCopy {

	@Override
	public Copy getCopy(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			Copy c = null;
	    	session.beginTransaction();
			c = session.load(Copy.class, id);
			session.getTransaction().commit();
			
			return c;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Copy> getCopies() {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			List<Copy> copies = new ArrayList<Copy>();
	    	session.beginTransaction();
	    	copies = session.createQuery("from Copy").list();
			session.getTransaction().commit();
			return copies;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteCopy(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
	    	session.beginTransaction();
	    	Copy c = session.load(Copy.class, id);
	    	c.getBook().getCopies().remove(c);
			session.delete(c);
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
	public boolean addCopies(List<Copy> copies) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			for(Copy c : copies) {
				session.save(c);
				c.getBook().getCopies().add(c);
			}
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
	public void repairCopy(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Copy c = session.load(Copy.class, id);
			c.setIsInReparation(false);
			c.setIsAvailable(true);
			session.save(c);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void sendRepair(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Copy c = session.load(Copy.class, id);
			c.setIsInReparation(true);
			c.setIsAvailable(false);
			session.save(c);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
