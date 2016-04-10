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
	
	private List<ReturnStatus> returnStatus;
	
	public ResponseLoginAnonymous() {
		super();
	}

	public ResponseLoginAnonymous(String ssoId, String password, List<ReturnStatus> returnStatus) {
		super();
		this.ssoId = ssoId;
		this.password = password;
		this.returnStatus = returnStatus;
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

	public List<ReturnStatus> getResponseStatus() {
		return returnStatus;
	}

	public void setResponseStatus(List<ReturnStatus> returnStatus) {
		this.returnStatus = returnStatus;
	}
	
}
