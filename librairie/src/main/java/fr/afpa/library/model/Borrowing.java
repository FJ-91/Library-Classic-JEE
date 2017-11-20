package fr.afpa.library.model;

import javax.persistence.*;

@Entity
@Table(name="borrowing")
public class Borrowing {
	
	private int id;
	private String borDate;
	private String borDateExpected;
	private String borDateReturn;
	private Subscriber sub;
	private Copy copy;
	
	public Borrowing() {}
	
	public Borrowing(String borDate, String borDateExpected, Subscriber sub, Copy copy) {
		this.borDate = borDate;
		this.borDateExpected = borDateExpected;
		this.sub = sub;
		this.copy = copy;
	}

	@Id
	@Column(name="bor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="bor_date")
	public String getBorDate() {
		return borDate;
	}

	public void setBorDate(String borDate) {
		this.borDate = borDate;
	}

	@Column(name="bor_expected_date")
	public String getBorDateExpected() {
		return borDateExpected;
	}

	public void setBorDateExpected(String borDateExpected) {
		this.borDateExpected = borDateExpected;
	}

	@Column(name="bor_return_date")
	public String getBorDateReturn() {
		return borDateReturn;
	}

	public void setBorDateReturn(String borDateReturn) {
		this.borDateReturn = borDateReturn;
	}

	@ManyToOne
	@JoinColumn(name="sub_id")
	public Subscriber getSub() {
		return sub;
	}

	public void setSub(Subscriber sub) {
		this.sub = sub;
	}
	
	@ManyToOne
	@JoinColumn(name="copy_id")
	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

	@Override
	public String toString() {
		return "Borrowing [id=" + id + ", borDate=" + borDate + ", borDateExpected=" + borDateExpected
				+ ", borDateReturn=" + borDateReturn + ", sub=" + sub + ", copy=" + copy + "]";
	}

}
