package vn.whoever.mainserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Token", uniqueConstraints = { 
		@UniqueConstraint(columnNames = {"idUser"}),
		@UniqueConstraint(columnNames = {"token"})
		})
public class Tokens implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1876564544343566L;

	@Id
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser")
	private Users users;
	
	@Column(name = "token", length = 32, nullable = false)
	private String token;
	
	@Column(name = "timeCreate")
	private Date timeCreate;
	
	@Column(name = "timeExp")
	private Date timeExp;
	
	
	public Tokens() {
		super();
	}
	
	public Tokens(Users users, String token) {
		super();
		this.users = users;
		this.token = token;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Date getTimeExp() {
		return timeExp;
	}

	public void setTimeExp(Date timeExp) {
		this.timeExp = timeExp;
	}
}
