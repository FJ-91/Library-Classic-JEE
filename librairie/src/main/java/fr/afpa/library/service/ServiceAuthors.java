package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.dao.DuplicateException;
import fr.afpa.library.dao.IDaoAuthors;
import fr.afpa.library.model.Author;

public class ServiceAuthors implements IServiceAuthors {
	
	private IDaoAuthors daoAuthors;
	
	public ServiceAuthors(IDaoAuthors daoAuthors) {
		this.daoAuthors = daoAuthors;
	}

	@Override
	public Author getAuthor(int id) {
		return daoAuthors.getAuthor(id);
	}

	@Override
	public List<Author> getAuthors() {
		return daoAuthors.getAuthors();
	}

	@Override
	public boolean deleteAuthor(int id) {
		return daoAuthors.deleteAuthor(id);
	}

	@Override
	public boolean addAuthor(Author a) throws DuplicateException {
		return daoAuthors.addAuthor(a);
	}

	@Override
	public boolean editAuthor(Author a) {
		return daoAuthors.editAuthor(a);
	}

}
