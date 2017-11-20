package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.model.Book;
import fr.afpa.library.model.Copy;

public interface IServiceCopy {
	
	public Copy getCopy(int id);

	public List<Copy> getCopies();

	public boolean deleteCopy(int id);

	public boolean addCopies(List<Copy> copies);
	
	public List<Book> getBooks();
	
	public Book getBook(String isbn);
	
	public void repairCopy(int id);

	public void sendRepair(int id);

}
