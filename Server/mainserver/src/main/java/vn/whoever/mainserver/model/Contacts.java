package vn.whoever.mainserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Contacts")
public class Contacts implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -15454289078L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idContact;

	@Column(name = "idUser", length = 16, nullable = false)
	private String idUser;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Contacts_Users", 
    	joinColumns = { @JoinColumn(name = "idContact") }, 
    	inverseJoinColumns = { @JoinColumn(name = "idUser") })
	private List<Users> users = new ArrayList<Users>();

	public Contacts() {
		super();
	}

	public Contacts(String idContact, String idUser) {
		super();
		this.idContact = idContact;
		this.idUser = idUser;
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

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
}
