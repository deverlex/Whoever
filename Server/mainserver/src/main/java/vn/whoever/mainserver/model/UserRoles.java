package vn.whoever.mainserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Users_Role", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"idUser", "role"}))
public class UserRoles {

	private int idRole;
	private int idUser;
	private String role = Roles.ANONYMOUS.getRole();
	
	public UserRoles() {
		super();
	}
	
	public UserRoles(int idRole, int idUser, String role) {
		super();
		this.idRole = idRole;
		this.idUser = idUser;
		this.role = role;
	}

	@Id
	@Column(name = "idRole")
	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser", nullable = false)
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Column(name = "role", length = 32, nullable = false)
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
