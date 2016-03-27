package vn.whoever.support.model.request;

public class RequestRegister {
	
	private String ssoId;
	private String password;
	private String nickName;
	
	public RequestRegister() {
		super();
	}

	public RequestRegister(String ssoId, String password, String nickName) {
		super();
		this.ssoId = ssoId;
		this.password = password;
		this.nickName = nickName;
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
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
