package vn.whoever.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USER", uniqueConstraints = { @UniqueConstraint(columnNames = {"username"})})
public class User {

	private String username;
	private String password;
	private String role;
	
	public User() {}
	
	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@Id
	@Column(name = "username", length = 32, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "role", nullable = false)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
