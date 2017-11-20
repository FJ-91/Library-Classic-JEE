package fr.afpa.library.dao;

import java.util.List;

import fr.afpa.library.model.Copy;

public interface IDaoCopy {
	
	public Copy getCopy(int id);

	public List<Copy> getCopies();

	public boolean deleteCopy(int id);

	public boolean addCopies(List<Copy> copies);
	
	public void repairCopy(int id);
	
	public void sendRepair(int id);

}
