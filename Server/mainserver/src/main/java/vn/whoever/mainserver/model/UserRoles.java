package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import vn.whoever.mainserver.model.supports.Roles;

@Entity
@Table(name = "Users_Role", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"idUser", "role"}))
public class UserRoles implements Serializable {

	private static final long serialVersionUID = 1687563455L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRole")
	private int idRole;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "idUser")
	private Users users;
	
	@Column(name = "role", length = 32, nullable = false)
	private String role = Roles.ANONYMOUS.getRole();
	
	public UserRoles() {
		super();
	}
	
	public UserRoles(int idRole, Users users, String role) {
		super();
		this.idRole = idRole;
		this.users = users;
		this.role = role;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	
	public Users getUser() {
		return users;
	}
	
	public void setUser(Users users) {
		this.users = users;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
