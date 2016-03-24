package vn.whoever.mainsite.model;

import java.util.Date;

public class UserStates {

	private String ssoId;
	private String state;
	private Date exp_date;
	
	public UserStates() {
		super();
	}
	
	public UserStates(String ssoId, String state, Date exp_date) {
		super();
		this.ssoId = ssoId;
		this.state = state;
		this.exp_date = exp_date;
	}

	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public Date getExp_date() {
		return exp_date;
	}
	
	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}
}
