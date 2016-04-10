package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = " Contacts_Users", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"idContact", "idUser"}))
public class ContactUsers implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 80799786671L;

	@Id
	@Column(name = "idContact", length = 16, nullable = false)
	private String idContact;
	
	@Id
	@Column(name = "idUser", length = 16, nullable = false)
	private String idUser;
	
	@Column(name = "followed")
	private Boolean followed;

	public ContactUsers() {
		super();
	}
	
	public ContactUsers(String idContact, String idUser, Boolean followed) {
		super();
		this.idContact = idContact;
		this.idUser = idUser;
		this.followed = followed;
	}

	public String getIdContact() {
		return idContact;
	}

	public void setIdContact(String idContact) {
		this.idContact = idContact;
	}
	
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Boolean getFollowed() {
		return followed;
	}

	public void setFollowed(Boolean followed) {
		this.followed = followed;
	}
}
