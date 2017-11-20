package fr.afpa.library.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import fr.afpa.library.model.Book;

public class DaoBook implements IDaoBook {

	@Override
	public Book getBook(String isbn) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			Book b = null;
	    	session.beginTransaction();
			b = session.load(Book.class, isbn);
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
	public List<Book> getBooks() {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
			List<Book> Books = new ArrayList<Book>();
	    	session.beginTransaction();
	    	Books = session.createQuery("from Book").list();
			session.getTransaction().commit();
			
			return Books;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteBook(String isbn) {
		Session session = HibernateConnect.getSessionFactory().openSession();
		try {
	    	session.beginTransaction();
	    	Book b = session.load(Book.class, isbn);
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
	public boolean addBook(Book b) throws DuplicateException{
		Session session = HibernateConnect.getSessionFactory().openSession();
			if(checkIsbn(b)) {
				throw new DuplicateException("This ISBN ( "+b.getISBN()+" ) already exists !");
			} else if(checkBook(b)) {
				throw new DuplicateException("The book "+b.getTitle()+" by "+b.getAuthor()+" already exists !");
			} else {
				session.beginTransaction();
				session.save(b);
				session.getTransaction().commit();
				
				return true;
			}
	}

	@Override
	public boolean editBook(Book b) throws DuplicateException {
		Session session = HibernateConnect.getSessionFactory().openSession();
		if(checkBook(b)) {
			throw new DuplicateException("The book "+b.getTitle()+" by "+b.getAuthor()+" already exists !");
		} else {
			session.beginTransaction();
			session.update(b);
			session.getTransaction().commit();
			
			return true;
		}
	}
	
	public boolean checkIsbn(Book b) {
		int i=0;
		boolean found = false;
		
		while(!found && i<getBooks().size()) {
			if(getBooks().get(i).getISBN().equals(b.getISBN())) {
				found = true;
			}
			i++;
		}
		return found;
	}
	
	public boolean checkBook(Book b) {
		boolean found = false;
		int i = 0;
		
		while(i<getBooks().size() && !found) {
			if(getBooks().get(i).equals(b) && !getBooks().get(i).getISBN().equals(b.getISBN())) {
				found = true;
			}
			i++;
		}
		return found;
	}

}
