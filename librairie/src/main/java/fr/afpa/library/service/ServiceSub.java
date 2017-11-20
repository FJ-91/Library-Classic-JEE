package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.dao.IDaoSub;
import fr.afpa.library.model.Subscriber;

public class ServiceSub implements IServiceSubs {
	
	private IDaoSub dao;
	
	public ServiceSub(IDaoSub dao) {
		this.dao = dao;
	}

	@Override
	public Subscriber getSub(int id) {
		return dao.getSub(id);
	}

	@Override
	public List<Subscriber> getSubs() {
		return dao.getSubs();
	}

	@Override
	public boolean deleteSub(int id) {
		return dao.deleteSub(id);
	}

	@Override
	public boolean addSub(Subscriber s) {
		return dao.addSub(s);
	}

	@Override
	public boolean editSub(Subscriber s) {
		return dao.editSub(s);
	}

}
