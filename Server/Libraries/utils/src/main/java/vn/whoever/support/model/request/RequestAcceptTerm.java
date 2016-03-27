package vn.whoever.support.model.request;

import java.util.Date;

public class RequestAcceptTerm {
	
	private String ssoId;
	private String langCode;
	private Date birthday;
	
	public RequestAcceptTerm() {
		super();
	}

	public RequestAcceptTerm(String ssoId, String langCode, Date birthday) {
		super();
		this.ssoId = ssoId;
		this.langCode = langCode;
		this.birthday = birthday;
	}

	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	public String getLangCode() {
		return langCode;
	}
	
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
