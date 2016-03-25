package vn.whoever.mainsite.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Countries {
	
	private String postalCode;
	private String name;
	
	public Countries() {
		super();
	}
	
	public Countries(String postalCode, String name) {
		super();
		this.postalCode = postalCode;
		this.name = name;
	}
	
	@Id
	@Column(name = "postalCode", length = 4, nullable = false)
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
