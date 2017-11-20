package fr.afpa.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "subscriber")
public class Subscriber {
	
	private int id;
	private String lastname;
	private String firstname;
	private String street;
	private String zipcode;
	private String city;
	private List<Borrowing> borrowings = new ArrayList<Borrowing>();
	private List<Borrowing> currentBorrowings = new ArrayList<Borrowing>();
	
	public Subscriber() {}
	
	public Subscriber(String lastname, String firstname, String street, String zipcode, String city) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}

	@Id
	@Column(name="sub_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="sub_ln")
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name="sub_fn")
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name="sub_street")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name="sub_zipcode")
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name="sub_city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="sub", orphanRemoval = true)
	public List<Borrowing> getBorrowings() {
		return borrowings;
	}

	public void setBorrowings(List<Borrowing> borrowings) {
		this.borrowings = borrowings;
	}
	
	@Transient
	public List<Borrowing> getCurrentBorrowings() {
		currentBorrowings.clear();
		for(Borrowing b : borrowings) {
			if(b.getBorDateReturn() == null) {
				currentBorrowings.add(b);
			}
		}
		return currentBorrowings;
	}

	public void setCurrentBorrowings(List<Borrowing> currentBorrowings) {
		this.currentBorrowings = currentBorrowings;
	}
	
	
	@Override
	public String toString() {
		return lastname + " " + firstname + " ( Id :"+id+" | Adress: " + street + ", " + zipcode + " " + city+" )";
	}


}
