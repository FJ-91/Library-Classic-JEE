package fr.afpa.library.dao;

import java.util.List;

import fr.afpa.library.model.Author;

public interface IDaoAuthors {

	public Author getAuthor(int id);

	public List<Author> getAuthors();

	public boolean deleteAuthor(int id);

	public boolean addAuthor(Author a) throws DuplicateException;

	public boolean editAuthor(Author a);
}
