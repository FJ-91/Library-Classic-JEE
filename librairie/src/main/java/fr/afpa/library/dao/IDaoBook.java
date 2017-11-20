package fr.afpa.library.dao;

import java.util.List;

import fr.afpa.library.model.Book;

public interface IDaoBook {
	
	public Book getBook(String isbn);

	public List<Book> getBooks();

	public boolean deleteBook(String isbn);

	public boolean addBook(Book b) throws DuplicateException;

	public boolean editBook(Book b) throws DuplicateException;

}
