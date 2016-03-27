package vn.whoever.support.model.request;

public class RequestLogin {
	
	private String ssoId;
	private String password;
	
	public RequestLogin() {
		super();
	}
	
	public RequestLogin(String ssoId, String password) {
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
