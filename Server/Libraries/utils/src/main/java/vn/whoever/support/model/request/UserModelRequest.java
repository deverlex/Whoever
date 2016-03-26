package vn.whoever.support.model.request;

public class UserModelRequest {
	
	private String email;
	private String password;
	
	public UserModelRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserModelRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
	
	
}
