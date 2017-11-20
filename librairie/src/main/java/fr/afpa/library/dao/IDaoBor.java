package fr.afpa.library.dao;

import java.util.List;

import fr.afpa.library.model.Borrowing;

public interface IDaoBor {
	
	public Borrowing getBor(int id);

	public List<Borrowing> getBors();

	public boolean deleteBor(int id);

	public boolean addBor(Borrowing b) throws DateException;

	public boolean editBor(Borrowing b) throws DateException;

	public boolean returnCopy(Borrowing b);

}
