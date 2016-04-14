package vn.whoever.support.response;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Genders;
import vn.whoever.support.model.utils.Privacies;

@XmlRootElement(name = "returnCallLogin")
@XmlType(propOrder = {"langName", "nickName", "birthday", "gender", "mobile",
		"email", "isOnline", "privacy"})
@JsonPropertyOrder(value = {"langName", "nickName", "birthday", "gender", "mobile",
		"email", "isOnline", "privacy"})
public class ReturnCallLogin {

	@XmlElement(name = "langName")
	private String langName; // native name or standard name
	
	@XmlElement(name = "nickName")
	private String nickName;
	
	@XmlElement(name = "birthday")
	private Date birthday;
	
	@XmlElement(name = "gender")
	private Genders gender = Genders.unknown;
	
	@XmlElement(name = "mobile")
	private String mobile;
	
	@XmlElement(name = "email")
	private String email;
	
	@XmlElement(name = "isOnline")
	private Boolean isOnline; // bat tat online
	
	@XmlElement(name = "privacy")
	private Privacies privacy = Privacies.normal; // che do an thong tin
	
	public ReturnCallLogin() {
		super();
	}

	public ReturnCallLogin(String languageName, String nickName, Date birthday, Genders gender, String mobile,
			String email, Boolean isOnline, Privacies privacy) {
		super();
		this.langName = languageName;
		this.nickName = nickName;
		this.birthday = birthday;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.isOnline = isOnline;
		this.privacy = privacy;
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
	
	public Genders getGender() {
		return gender;
	}
	
	public void setGender(Genders gender) {
		this.gender = gender;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	public Boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Privacies getPrivacy() {
		return privacy;
	}
	
	public void setPrivacy(Privacies privacy) {
		this.privacy = privacy;
	}
}
