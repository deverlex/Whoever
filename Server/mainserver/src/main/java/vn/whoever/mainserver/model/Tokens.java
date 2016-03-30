package vn.whoever.mainserver.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Token", uniqueConstraints = @UniqueConstraint(columnNames = "token"))
public class Tokens {
	
	@Id
	@GeneratedValue
	private long idUser;
	
	@Column(name = "token", length = 32, nullable = false)
	private String token;
	
	@Column(name = "exp_date", nullable = false)
	private Date exp_date;
	
	public Tokens() {
		super();
	}

	public Tokens(long idUser, String token, Date exp_date) {
		super();
		this.idUser = idUser;
		this.token = token;
		this.exp_date = exp_date;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExp_date() {
		return exp_date;
	}

	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}
	
}
