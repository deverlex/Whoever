package vn.whoever.support.model.request;

import java.util.List;

public class RequestStatus {
	
	private String ssoId;
	private String password;
	
	private List<RequestComment> status;

	public RequestStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestStatus(String ssoId, String password, List<RequestComment> status) {
		super();
		this.ssoId = ssoId;
		this.password = password;
		this.status = status;
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

	public List<RequestComment> getStatus() {
		return status;
	}

	public void setStatus(List<RequestComment> status) {
		this.status = status;
	}
	
	
}
