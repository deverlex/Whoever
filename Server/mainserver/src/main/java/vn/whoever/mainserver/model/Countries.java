package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "countries", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"postalCode", "name"}))
public class Countries implements Serializable {

	private static final long serialVersionUID = 165643342L;

	@Id
	@Column(name = "idCountry")
	private int idCountry;
	
	@Column(name = "postalCode", length = 6, nullable = false)
	private String postalCode;
	
	@Column(name = "name", length = 32, nullable = false)
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
	
	public int getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
