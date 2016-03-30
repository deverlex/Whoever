package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import vn.whoever.support.model.utils.Roles;

@Entity
@Table(name = "Users_Role", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"idUser", "role"}))
public class UserRoles implements Serializable {

	private static final long serialVersionUID = 1687563455L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRole")
	private long idRole;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idUser")
	private Users users;
	
	@Column(name = "role", length = 32, nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles roles = Roles.USER;
	
	public UserRoles() {
		super();
	}
	
	public UserRoles(Users users, Roles roles) {
		super();
		this.users = users;
		this.roles = roles;
	}



	public UserRoles(long idRole, Users users, Roles roles) {
		super();
		this.idRole = idRole;
		this.users = users;
		this.roles = roles;
	}

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
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
