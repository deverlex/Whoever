package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import vn.whoever.support.model.utils.Interacts;

@Entity
@Table(name = "status_users", uniqueConstraints = @UniqueConstraint(columnNames = { "idStatus", "idUser" }))
@DynamicUpdate(value = true)
public class StatusUsers implements Serializable {

	private static final long serialVersionUID = 1939789373L;

	@Id
	@Column(name = "idStatus", nullable = false)
	private String idStatus;

	@Id
	@Column(name = "idUser", nullable = false)
	private String idUser;

	@Column(name = "interact", nullable = false)
	@Enumerated(EnumType.STRING)
	private Interacts interact;

	public StatusUsers() {
		super();
	}

	public StatusUsers(String idStatus, String idUser, Interacts interact) {
		super();
		this.idStatus = idStatus;
		this.idUser = idUser;
		this.interact = interact;
	}

	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Interacts getInteract() {
		return interact;
	}

	public void setInteract(Interacts interact) {
		this.interact = interact;
	}
}
