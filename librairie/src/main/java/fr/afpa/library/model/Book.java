package fr.afpa.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="book")
public class Book {
	
	private String ISBN;
	private String title;
	private Author author;
	private Catalogue cat;
	private List<Copy> copies = new ArrayList<Copy>();
	private int copiesUnavailable = 0;
	
	public Book() {}

	public Book(String iSBN, String title, Author author, Catalogue cat) {
		ISBN = iSBN;
		this.title = title;
		this.author = author;
		this.cat = cat;
	}

	@Id
	@Column(name="book_isbn")
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	@Column(name="book_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne
	@JoinColumn(name="author_id")
	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="book", cascade=CascadeType.ALL)
	public List<Copy> getCopies() {
		return copies;
	}
	
	public void setCopies(List<Copy> copies) {
		this.copies = copies;
	}
	
	@ManyToOne
	@JoinColumn(name="cat_id")
	public Catalogue getCat() {
		return cat;
	}
	
	public void setCat(Catalogue cat) {
		this.cat = cat;
	}
	
	@Override
	public String toString() {
		if(author == null) return title+" (ISBN = "+ISBN+") by ' Unknown Author ' ";
		else return title+" (ISBN = "+ISBN+") by "+author.getLastname()+" "+author.getFirstname();
	}
	
	@Override
	public boolean equals(Object a) {
	    if (a == null) return false;
	    
	    if(! ((Book)a).getTitle().toLowerCase().equals(title.toLowerCase())) return false;
	    
	    if(! ((Book)a).getAuthor().equals(author)) return false;
	    
	    if(! ((Book)a).getCat().getName().toLowerCase().equals(cat.getName().toLowerCase())) return false;
	    
	    return true;
	}

	@Transient
	public int getCopiesUnavailable() {
		for(Copy c : copies) {
			if(!c.getIsAvailable()) {
				copiesUnavailable++;
			}
		}
		return copiesUnavailable;
	}

	public void setCopiesUnavailable(int copiesUnavailable) {
		this.copiesUnavailable = copiesUnavailable;
	}
	
}
