package vn.whoever.mainserver.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Contacts")
public class Contacts implements Serializable {

	private static final long serialVersionUID = -15454289078L;

	@Id
	@Column(name = "idContact", length = 16, nullable = false, unique = true)
	private String idContact;

	@Column(name = "idUser", length = 16, nullable = false)
	private String idUser;

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
}
