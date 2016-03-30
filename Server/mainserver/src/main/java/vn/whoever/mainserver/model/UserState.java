package vn.whoever.mainserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User_State")
public class UserState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12121212434L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser", nullable = false)
	private long idUser;
	
	@Column(name = "state", length = 12, nullable = true)
	private String state;
	
	@Column(name = "timeout")
	private Date timeout;
	
	@Column(name = "exp_date")
	private Date exp_date;
	
	public UserState() {
		super();
	}
		
	public UserState(long idUser, String state, Date timeout, Date exp_date) {
		super();
		this.idUser = idUser;
		this.state = state;
		this.timeout = timeout;
		this.exp_date = exp_date;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public Date getTimeout() {
		return timeout;
	}

	public void setTimeout(Date timeout) {
		this.timeout = timeout;
	}

	public Date getExp_date() {
		return exp_date;
	}
	
	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}
}
