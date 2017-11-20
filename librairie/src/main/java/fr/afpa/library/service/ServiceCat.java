package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.dao.DuplicateException;
import fr.afpa.library.dao.IDaoCat;
import fr.afpa.library.model.Catalogue;

public class ServiceCat implements IServiceCat {
	
	private IDaoCat dao;
	
	public ServiceCat(IDaoCat dao) {
		this.dao = dao;
	}

	@Override
	public Catalogue getCat(int id) {
		return dao.getCat(id);
	}

	@Override
	public List<Catalogue> getCats() {
		return dao.getCats();
	}

	@Override
	public boolean deleteCat(int id) {
		return dao.deleteCat(id);
	}

	@Override
	public boolean addCat(Catalogue c) throws DuplicateException {
		return dao.addCat(c);
	}

	@Override
	public boolean editCat(Catalogue c) throws DuplicateException {
		return dao.editCat(c);
	}

}
