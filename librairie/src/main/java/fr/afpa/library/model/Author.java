package fr.afpa.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="author")
public class Author {

	private int id;
	private String lastname;
	private String firstname;
	private String dob;
	private List<Book> booksWritten = new ArrayList<Book>();

	public Author() {}
	
	public Author(String lastname, String firstname, String dob) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.dob = dob;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="author_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="author_ln")
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name="author_fn")
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name="author_dob")
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="author")
	public List<Book> getBooksWritten() {
		return booksWritten;
	}

	public void setBooksWritten(List<Book> booksWritten) {
		this.booksWritten = booksWritten;
	}

	@Override
	public String toString() {
		return lastname+" "+firstname+" ("+dob+")";
	}
	
	@Override
	public boolean equals(Object a) {
	    if (a == null) return false;
	    
	    if(! ((Author)a).getFirstname().toLowerCase().equals(firstname.toLowerCase())) return false;
	    
	    if(! ((Author)a).getLastname().toLowerCase().equals(lastname.toLowerCase())) return false;
	    
	    if(! ((Author)a).getDob().equals(dob)) return false;
	    
	    return true;
	}

	
}
