package vn.whoever.mainsite.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Users_State", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"ssoId", "state"}))
public class UserStates {

	private String ssoId;
	private String state;
	private Date timeout;
	private Date exp_date;
	
	public UserStates() {
		super();
	}
	
	public UserStates(String ssoId, String state, Date timeout,Date exp_date) {
		super();
		this.ssoId = ssoId;
		this.state = state;
		this.timeout = timeout;
		this.exp_date = exp_date;
	}

	@Id
	@Column(name = "ssoId", length = 32, unique = true, nullable = true)
	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
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
