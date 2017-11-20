package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.model.*;

public interface IServiceSubs {

	public Subscriber getSub(int id);

	public List<Subscriber> getSubs();

	public boolean deleteSub(int id);

	public boolean addSub(Subscriber s);

	public boolean editSub(Subscriber s);
	
}
