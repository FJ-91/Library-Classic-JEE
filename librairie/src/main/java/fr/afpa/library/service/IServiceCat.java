package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.dao.DuplicateException;
import fr.afpa.library.model.Catalogue;

public interface IServiceCat {
	
	public Catalogue getCat(int id);

	public List<Catalogue> getCats();

	public boolean deleteCat(int id);

	public boolean addCat(Catalogue c) throws DuplicateException;

	public boolean editCat(Catalogue c) throws DuplicateException;
	
}
