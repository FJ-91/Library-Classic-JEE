package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.dao.DuplicateException;
import fr.afpa.library.model.Author;

public interface IServiceAuthors {

	public Author getAuthor(int id);

	public List<Author> getAuthors();

	public boolean deleteAuthor(int id);

	public boolean addAuthor(Author a) throws DuplicateException;

	public boolean editAuthor(Author a);
}
