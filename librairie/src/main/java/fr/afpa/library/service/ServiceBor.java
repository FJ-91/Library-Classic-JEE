package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.dao.DaoCopy;
import fr.afpa.library.dao.DaoSub;
import fr.afpa.library.dao.DateException;
import fr.afpa.library.dao.IDaoBor;
import fr.afpa.library.model.Borrowing;
import fr.afpa.library.model.Copy;
import fr.afpa.library.model.Subscriber;

public class ServiceBor implements IServiceBor {
	
	private IDaoBor daoBor;
	private DaoCopy daoCopy = new DaoCopy();
	private DaoSub daoSub = new DaoSub();
	
	public ServiceBor(IDaoBor daoBor) {
		this.daoBor = daoBor;
	}

	@Override
	public Borrowing getBor(int id) {
		return daoBor.getBor(id);
	}

	@Override
	public List<Borrowing> getBors() {
		return daoBor.getBors();
	}

	@Override
	public boolean deleteBor(int id) {
		return daoBor.deleteBor(id);
	}

	@Override
	public boolean addBor(Borrowing b) throws DateException {
		return daoBor.addBor(b);
	}

	@Override
	public boolean editBor(Borrowing b) throws DateException {
		return daoBor.editBor(b);
	}
	
	@Override
	public Copy getCopy(int id) {
		return daoCopy.getCopy(id);
	}

	@Override
	public List<Copy> getCopies() {
		return daoCopy.getCopies();
	}

	@Override
	public Subscriber getSub(int id) {
		return daoSub.getSub(id);
	}

	@Override
	public List<Subscriber> getSubs() {
		return daoSub.getSubs();
	}

	@Override
	public boolean returnCopy(Borrowing b) {
		return daoBor.returnCopy(b);
	}

}
