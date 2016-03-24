package vn.whoever.mainsite.model;

import java.util.Date;

public class User {

	private String ssoId;
	private String email;
	private String password;
	private Date birthday;
	
	public User() {}
	
	public User(String ssoId, String email, 
			String password, Date birthday) {
		this.ssoId = ssoId;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
	}
	
	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
