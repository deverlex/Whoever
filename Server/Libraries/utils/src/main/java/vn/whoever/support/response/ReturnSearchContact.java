package vn.whoever.support.response;

import java.io.Serializable;

public class ReturnSearchContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 14309420730173L;
	
	private String ssoId;
	private String nickName;
	private String avatart;
	
	public ReturnSearchContact() {
		super();
	}

	public ReturnSearchContact(String ssoId, String nickName, String avatart) {
		super();
		this.ssoId = ssoId;
		this.nickName = nickName;
		this.avatart = avatart;
	}

	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getAvatart() {
		return avatart;
	}
	
	public void setAvatart(String avatart) {
		this.avatart = avatart;
	}
}
