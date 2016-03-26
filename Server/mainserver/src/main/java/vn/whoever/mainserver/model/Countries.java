package vn.whoever.mainserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "countries", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"postalCode", "name"}))
public class Countries {

	private int idCountry;
	private String postalCode;
	private String name;

	public Countries() {
		super();
	}

	public Countries(int idCountry, String postalCode, String name) {
		super();
		this.idCountry = idCountry;
		this.postalCode = postalCode;
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	@Column(name = "postalCode", length = 6, nullable = false)
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "name", length = 32, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
