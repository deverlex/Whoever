package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import vn.whoever.support.model.utils.Interacts;

@Entity
@Table(name = "status_users", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"idStatus", "idStatus"}))
public class StatusUsers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1939789373L;
	private Status status;
	private Users users;
	private Interacts interacts = Interacts.normal;
	
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
