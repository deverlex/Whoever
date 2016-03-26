package vn.whoever.support.model.request;

public class UserModelRequest {
	
	private String ssoId;
	private String password;
	
	public UserModelRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserModelRequest(String ssoId, String password) {
		super();
		this.ssoId = ssoId;
		this.password = password;
	}
	
	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
