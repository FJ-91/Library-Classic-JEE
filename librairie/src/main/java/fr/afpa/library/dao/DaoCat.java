package fr.afpa.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import fr.afpa.library.model.Catalogue;

public class DaoCat implements IDaoCat {

	@Override
	public Catalogue getCat(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			Catalogue c = null;
	    	session.beginTransaction();
			c = session.load(Catalogue.class, id);
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
	public List<Catalogue> getCats() {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			List<Catalogue> catalogues = new ArrayList<Catalogue>();
	    	session.beginTransaction();
	    	catalogues = session.createQuery("from Catalogue").list();
			session.getTransaction().commit();
			return catalogues;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteCat(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
	    	session.beginTransaction();
	    	Catalogue c = session.load(Catalogue.class, id);
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
	public boolean addCat(Catalogue c) throws DuplicateException {
		Session session = HibernateConnect.getSessionFactory().openSession();
		if(checkDupCat(c)) {
			throw new DuplicateException("The "+c.getName()+" catalogue already exists !");
		} else {
			session.beginTransaction();
			session.save(c);
			session.getTransaction().commit();
			
			return true;
		}
	}

	@Override
	public boolean editCat(Catalogue c) throws DuplicateException {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(c);
			session.getTransaction().commit();
			
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	private boolean checkDupCat(Catalogue c) {
		boolean found = false;
		int i = 0;
		
		while(i<getCats().size() && !found) {
			if(getCats().get(i).getName().toLowerCase().equals(c.getName().toLowerCase()) && getCats().get(i).getId()!=c.getId()) {
				found = true;
			}
			i++;
		}
		
		return found;
	}

}
