package fr.afpa.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="catalogue")
public class Catalogue {
	
	private int id;
	private String name;
	private List<Book> books = new ArrayList<Book>();
	private int checkBookAvailable = 0;
	
	public Catalogue() {}
	
	public Catalogue(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cat_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="cat_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="cat", cascade=CascadeType.ALL)
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	@Transient
	public int getCheckBookAvailable() {
		@SuppressWarnings("unused")
		boolean bool = false;
		int i=0;
		while(i<books.size() && !false) {
			if(books.get(i).getCopiesUnavailable() != 0) {
				bool = true;
				checkBookAvailable++;
			}
			i++;
		}
		return checkBookAvailable;
	}

	public void setCheckBookAvailable(int checkBookAvailable) {
		this.checkBookAvailable = checkBookAvailable;
	}

	@Override
	public String toString() {
		return name;
	}


}
