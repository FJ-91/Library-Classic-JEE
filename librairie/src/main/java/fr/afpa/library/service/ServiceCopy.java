package fr.afpa.library.service;

import java.util.List;

import fr.afpa.library.dao.IDaoBook;
import fr.afpa.library.dao.IDaoCopy;
import fr.afpa.library.model.Book;
import fr.afpa.library.model.Copy;

public class ServiceCopy implements IServiceCopy {
	
	private IDaoCopy daoCopy;
	private IDaoBook daoBook;
	
	public ServiceCopy(IDaoCopy DaoCopy, IDaoBook DaoBook) {
		daoCopy = DaoCopy;
		daoBook = DaoBook;
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
	public boolean deleteCopy(int id) {
		return daoCopy.deleteCopy(id);
	}

	@Override
	public boolean addCopies(List<Copy> copies) {
		return daoCopy.addCopies(copies);
	}
	
	public List<Book> getBooks() {
		return daoBook.getBooks();
	}

	@Override
	public Book getBook(String isbn) {
		return daoBook.getBook(isbn);
	}
	
	@Override
	public void sendRepair(int id) {
		daoCopy.sendRepair(id);
	}

	@Override
	public void repairCopy(int id) {
		daoCopy.repairCopy(id);
	}

}
