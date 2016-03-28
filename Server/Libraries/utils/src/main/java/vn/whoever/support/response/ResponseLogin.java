package vn.whoever.support.response;

import vn.whoever.support.model.utils.Roles;

public class ResponseLogin {
	
	private String result;
	private Roles roles;
	private String note;
	
	public ResponseLogin() {
		super();
	}
	
	public ResponseLogin(String result, Roles roles, String note) {
		super();
		this.result = result;
		this.roles = roles;
		this.note = note;
	}

	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public Roles getRoles() {
		return roles;
	}
	
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
}
