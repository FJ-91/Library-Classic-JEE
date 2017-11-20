package fr.afpa.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import fr.afpa.library.model.Author;

public class DaoAuthors implements IDaoAuthors {
	

	public Author getAuthor(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			Author a = null;
	    	session.beginTransaction();
			a = session.load(Author.class, id);
			session.getTransaction().commit();
			
			return a;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Author> getAuthors() {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			List<Author> authors = new ArrayList<Author>();
	    	session.beginTransaction();
			authors = session.createQuery("from Author").list();
			session.getTransaction().commit();
			
			return authors;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public boolean deleteAuthor(int id) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
	    	session.beginTransaction();
	    	Author a = session.load(Author.class, id);
	    	for(int i = 0; i<a.getBooksWritten().size(); i++) {
	    		a.getBooksWritten().get(i).setAuthor(null);
	    	}
			session.delete(a);
			session.getTransaction().commit();
			
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public boolean addAuthor(Author a) throws DuplicateException{
    	Session session = HibernateConnect.getSessionFactory().openSession();

    		if(checkAuthor(a)) {
        		throw new DuplicateException(a+" already exists");
        	} else {
        		session.beginTransaction();
    			session.save(a);
    			session.getTransaction().commit();
    			
    			return true;
        	}
	}

	public boolean editAuthor(Author a) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
	    	session.beginTransaction();
			session.update(a);
			session.getTransaction().commit();
			
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	public boolean checkAuthor(Author a) {
		boolean found = false;
		int i = 0;
		
		while(i<getAuthors().size() && !found) {
			if(getAuthors().get(i).equals(a)) {
				found = true;
			}
			i++;
		}
		
		return found;
	}

}
