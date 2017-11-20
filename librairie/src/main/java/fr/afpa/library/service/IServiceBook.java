package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.dao.DuplicateException;
import fr.afpa.library.model.Author;
import fr.afpa.library.model.Book;
import fr.afpa.library.model.Catalogue;

public interface IServiceBook {
	
	public Book getBook(String isbn);

	public List<Book> getBooks();

	public boolean addBook(Book b) throws DuplicateException;

	public boolean editBook(Book b) throws DuplicateException;

	public boolean deleteBook(String isbn);
	
	public List<Author> getAuthors();
	
	public List<Catalogue> getCats();
	
	public Author getAuthor(int id);
	
	public Catalogue getCat(int id);

}
