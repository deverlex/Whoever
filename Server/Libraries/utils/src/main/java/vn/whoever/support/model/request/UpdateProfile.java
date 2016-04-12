package vn.whoever.support.model.request;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import vn.whoever.support.model.utils.Genders;
import vn.whoever.support.model.utils.Privacies;

@XmlRootElement(name = "updateProfile")
public class UpdateProfile {
	
	@XmlElement(name = "nickName")
	private String nickName;
	
	@XmlElement(name = "birthday")
	private Date birthday;
	
	@XmlElement(name = "gender")
	private Genders gender = Genders.unknown;

	@XmlElement(name = "mobile")
	private String mobile;
	
	@XmlElement(name = "privacy")
	private Privacies privacy = Privacies.normal;

	public UpdateProfile() {
		super();
	}

	public UpdateProfile(String nickName, Date birthday, Genders gender, String mobile, Privacies privacy) {
		super();
		this.nickName = nickName;
		this.birthday = birthday;
		this.gender = gender;
		this.mobile = mobile;
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

	public Privacies getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacies privacy) {
		this.privacy = privacy;
	}
}
