package vn.whoever.support.model.request;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "callRegister")
@XmlType(propOrder = { "ssoId", "password", "nickName", "birthday", "langCode" })
@JsonPropertyOrder(value = { "ssoId", "password", "nickName", "birthday", "langCode" })
public class CallRegister implements Serializable {
	
	private static final long serialVersionUID = 1585882942332L;

	@XmlElement(name = "ssoId", required = true)
	private String ssoId;

	@XmlElement(name = "password", required = true)
	private String password;

	@XmlElement(name = "nickName")
	private String nickName;

	@XmlElement(name = "birthday")
	private Date birthday;

	@XmlElement(name = "langCode", required = true)
	private String langCode;

	public CallRegister() {
		super();
	}

	public CallRegister(String ssoId, String password, String nickName, Date birthday, String langCode) {
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
