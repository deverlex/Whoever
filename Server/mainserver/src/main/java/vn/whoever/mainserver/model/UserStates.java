package vn.whoever.mainserver.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User_State")
public class UserStates {

	private int idUser;
	private String state;
	private Date timeout;
	private Date exp_date;
	
	public UserStates() {
		super();
	}
		
	public UserStates(int idUser, String state, Date timeout, Date exp_date) {
		super();
		this.idUser = idUser;
		this.state = state;
		this.timeout = timeout;
		this.exp_date = exp_date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Column(name = "state", length = 12, nullable = true)
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "timeout")
	public Date getTimeout() {
		return timeout;
	}

	public void setTimeout(Date timeout) {
		this.timeout = timeout;
	}

	@Column(name = "exp_date")
	public Date getExp_date() {
		return exp_date;
	}
	
	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}
}
