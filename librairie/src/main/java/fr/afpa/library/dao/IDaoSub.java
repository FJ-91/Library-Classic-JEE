package fr.afpa.library.dao;

import java.util.List;

import fr.afpa.library.model.Subscriber;

public interface IDaoSub {
	
	public Subscriber getSub(int id);

	public List<Subscriber> getSubs();

	public boolean deleteSub(int id);

	public boolean addSub(Subscriber s);

	public boolean editSub(Subscriber s);

}
