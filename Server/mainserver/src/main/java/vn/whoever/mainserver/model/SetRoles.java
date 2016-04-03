package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import vn.whoever.support.model.utils.Roles;

@Entity
@Table(name = "Roles", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"idUser", "role"}))
public class SetRoles implements Serializable {

	private static final long serialVersionUID = 1687563455L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "idUser")
	private Users users;
	
	@Id
	@Column(name = "role", length = 12, nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles roles = Roles.USER;
	
	public SetRoles() {
		super();
	}
	
	public SetRoles(Users users, Roles roles) {
		super();
		this.users = users;
		this.roles = roles;
	}
	
	public Users getUser() {
		return users;
	}
	
	public void setUser(Users users) {
		this.users = users;
	}
	
	public Roles getRoles() {
		return roles;
	}
	
	public void setRole(Roles roles) {
		this.roles = roles;
	}
}
