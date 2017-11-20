package fr.afpa.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="copy")
public class Copy {
	
	private int id;
	private Book book;
	private List<Borrowing> bors = new ArrayList<Borrowing>();
	private boolean isAvailable;
	private boolean isInReparation;
	private Subscriber currentSub;

	public Copy() {}
	
	public Copy(int id, Book book) {
		this.id = id;
		this.book = book;
	}
	
	public Copy(Book book) {
		this.book = book;
	}
	

	public Copy(Book book, boolean isAvailable, boolean isInReparation) {
		this.book = book;
		this.isAvailable = isAvailable;
		this.isInReparation = isInReparation;
	}

	@Id
	@Column(name="copy_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="book_isbn")
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}


	@Column(name="copy_isAvailable")
	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		if(book.getAuthor() != null) return "Copy n°"+id+" | Title : "+book.getTitle()+" by "+book.getAuthor().getLastname()+" "+book.getAuthor().getFirstname();
		else return "Copy n°"+id+" | Title : "+book.getTitle()+" by 'Unknown Author";
	}

	@OneToMany(mappedBy="copy", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Borrowing> getBors() {
		return bors;
	}

	public void setBors(List<Borrowing> bors) {
		this.bors = bors;
	}

	@Column(name="copy_isInReparation")
	public boolean getIsInReparation() {
		return isInReparation;
	}

	public void setIsInReparation(boolean isInReparation) {
		this.isInReparation = isInReparation;
	}

	@Transient
	public Subscriber getCurrentSub() {
		for(Borrowing b : bors) {
			if(b.getBorDateReturn() == null)
				currentSub = b.getSub();
			break;
		}
		return currentSub;
	}

	public void setCurrentSub(Subscriber currentSub) {
		this.currentSub = currentSub;
	}
	
	
}
