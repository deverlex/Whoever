package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import vn.whoever.support.model.utils.Interacts;

@Entity
@Table(name = "status_users", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"idStatus", "idUser"}))
@DynamicUpdate(value  = true)
public class StatusUsers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1939789373L;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idStatus")
	private Status status;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idUser")
	private Users users;
	
	@Column(name = "interact", nullable = false)
	@Enumerated(EnumType.STRING)
	private Interacts interacts;
	
	public StatusUsers() {
		super();
	}

	public StatusUsers(Status status, Users users, Interacts interacts) {
		super();
		this.status = status;
		this.users = users;
		this.interacts = interacts;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Users getUsers() {
		return users;
	}
	
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public Interacts getInteracts() {
		return interacts;
	}
	
	public void setInteracts(Interacts interacts) {
		this.interacts = interacts;
	}
}
