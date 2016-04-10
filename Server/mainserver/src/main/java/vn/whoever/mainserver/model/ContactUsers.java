package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Contacts_Users", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"idContact", "idUser"}))
public class ContactUsers implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 80799786671L;

	@Column(name = "idContact", length = 16, nullable = false)
	private String idContact;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser")
	private Users users;
	
	@Column(name = "followed")
	private Boolean followed;

	public ContactUsers() {
		super();
	}
	
	public ContactUsers(String idContact, Users users, Boolean followed) {
		super();
		this.idContact = idContact;
		this.users = users;
		this.followed = followed;
	}
	
	public String getIdContact() {
		return idContact;
	}

	public void setIdContact(String idContact) {
		this.idContact = idContact;
	}
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Boolean getFollowed() {
		return followed;
	}

	public void setFollowed(Boolean followed) {
		this.followed = followed;
	}
}
