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
	@UniqueConstraint(columnNames = {"ssoId", "role"}))
public class UserRoles {

	private int idUserRole;
	private String ssoId;
	private String role = Roles.ANONYMOUS.getRole();
	
	public UserRoles() {
		super();
	}
	
	public UserRoles(int idUserRole, String ssoId, String role) {
		super();
		this.idUserRole = idUserRole;
		this.ssoId = ssoId;
		this.role = role;
	}
	
	@Id
	@Column(name = "idUserRole")
	public int getIdUserRole() {
		return idUserRole;
	}

	public void setIdUserRole(int idUserRole) {
		this.idUserRole = idUserRole;
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ssoId", length = 32, nullable = false)
	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	@Column(name = "role", length = 32, nullable = false)
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
