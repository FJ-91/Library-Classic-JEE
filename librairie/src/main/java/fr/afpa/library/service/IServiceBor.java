package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.dao.DateException;
import fr.afpa.library.model.Borrowing;
import fr.afpa.library.model.Copy;
import fr.afpa.library.model.Subscriber;

public interface IServiceBor {
	
	public Borrowing getBor(int id);

	public List<Borrowing> getBors();

	public boolean deleteBor(int id);

	public boolean addBor(Borrowing b) throws DateException;

	public boolean editBor(Borrowing b) throws DateException;
	
	public boolean returnCopy(Borrowing b);
	
	public Copy getCopy(int id);

	public List<Copy> getCopies();
	
	public Subscriber getSub(int id);

	public List<Subscriber> getSubs();

}
