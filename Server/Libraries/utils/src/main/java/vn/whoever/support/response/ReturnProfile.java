package vn.whoever.support.response;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Genders;
import vn.whoever.support.model.utils.Privacies;

@XmlRootElement(name = "returnProfile")
@XmlType(propOrder = {"nickName", "birthday", "gender", "mobile", "email", "privacy"})
@JsonPropertyOrder(value = {"nickName", "birthday", "gender", "mobile", "email", "privacy"})
public class ReturnProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -574323422431L;

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
	
	@XmlElement(name = "privacy")
	private Privacies privacy = Privacies.normal;
	
	public ReturnProfile() {
		super();
	}

	public ReturnProfile(String nickName, Date birthday, Genders gender, String mobile, String email,
			Privacies privacy) {
		super();
		this.nickName = nickName;
		this.birthday = birthday;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
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

	public Privacies getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacies privacy) {
		this.privacy = privacy;
	}
}
