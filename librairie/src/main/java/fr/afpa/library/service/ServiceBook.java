package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.dao.DaoAuthors;
import fr.afpa.library.dao.DaoCat;
import fr.afpa.library.dao.DuplicateException;
import fr.afpa.library.dao.IDaoBook;
import fr.afpa.library.model.Author;
import fr.afpa.library.model.Book;
import fr.afpa.library.model.Catalogue;

public class ServiceBook implements IServiceBook {
	
	private IDaoBook daoBook;
	private DaoAuthors daoAuthors = new DaoAuthors();
	private DaoCat daoCat = new DaoCat();
	
	public ServiceBook(IDaoBook daoBook) {
		this.daoBook = daoBook;
	}

	@Override
	public Book getBook(String isbn) {
		return daoBook.getBook(isbn);
	}

	@Override
	public List<Book> getBooks() {
		return daoBook.getBooks();
	}

	@Override
	public boolean deleteBook(String isbn) {
		return daoBook.deleteBook(isbn);
	}

	@Override
	public boolean addBook(Book b) throws DuplicateException {
		return daoBook.addBook(b);
	}

	@Override
	public boolean editBook(Book b) throws DuplicateException {
		return daoBook.editBook(b);
	}
	
	@Override
	public List<Author> getAuthors(){
		return daoAuthors.getAuthors();
	}
	
	@Override
	public List<Catalogue> getCats(){
		return daoCat.getCats();
	}
	
	@Override
	public Author getAuthor(int id) {
		return daoAuthors.getAuthor(id);
	}
	
	@Override
	public Catalogue getCat(int id) {
		return daoCat.getCat(id);
	}

}
