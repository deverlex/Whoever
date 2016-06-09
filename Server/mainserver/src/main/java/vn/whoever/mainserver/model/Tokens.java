package vn.whoever.mainserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import vn.whoever.support.utils.FormatDate;

@Entity
@Table(name = "Token", uniqueConstraints = { 
		@UniqueConstraint(columnNames = {"idUser"}),
		@UniqueConstraint(columnNames = {"token"})
		})
@DynamicUpdate(value = true)
public class Tokens implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1876564544343566L;
	
	@Id
	@Column(name = "idUser", length = 16)
	private String idUser;
	
	@Column(name = "token", length = 32, nullable = false)
	private String token;
	
	@Column(name = "timeExp")
	private Date timeExp;
	
	
	public Tokens() {
		super();
	}

	public Tokens(String idUser, String token) {
		super();
		this.idUser = idUser;
		this.token = token;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTimeExp() {
		return (new FormatDate(timeExp)).toDateString();
	}

	public void setTimeExp(String timeExp) {
		this.timeExp = (new FormatDate(timeExp)).toDate();
	}
	
	public void setTimeExp(Date timeExp) {
		this.timeExp = timeExp;
	}
	
}
