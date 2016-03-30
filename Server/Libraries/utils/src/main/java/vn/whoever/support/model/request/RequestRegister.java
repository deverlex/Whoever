package vn.whoever.support.model.request;

import java.util.Date;

public class RequestRegister {
	
	private String ssoId;
	private String password;
	private String nickName;
	private Date birthday;
	private String langCode;
	
	public RequestRegister() {
		super();
	}

	public RequestRegister(String ssoId, String password, String nickName, Date birthday, String langCode) {
		super();
		this.ssoId = ssoId;
		this.password = password;
		this.nickName = nickName;
		this.birthday = birthday;
		this.langCode = langCode;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
}
