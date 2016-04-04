package vn.whoever.support.response;

import java.io.Serializable;
import java.util.List;

public class ResponseLoginAnonymous implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -19497398L;
	private String ssoId;
	private String password;
	
	private List<ResponseStatus> responseStatus;
	
	public ResponseLoginAnonymous() {
		super();
	}

	public ResponseLoginAnonymous(String ssoId, String password, List<ResponseStatus> responseStatus) {
		super();
		this.ssoId = ssoId;
		this.password = password;
		this.responseStatus = responseStatus;
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

	public List<ResponseStatus> getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(List<ResponseStatus> responseStatus) {
		this.responseStatus = responseStatus;
	}
	
}
