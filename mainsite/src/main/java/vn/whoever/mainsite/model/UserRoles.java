package vn.whoever.mainsite.model;

public class UserRoles {

	private String ssoId;
	private String role;
	
	public UserRoles() {
		super();
	}
	
	public UserRoles(String ssoId, String role) {
		super();
		this.ssoId = ssoId;
		this.role = role;
	}
	
	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
