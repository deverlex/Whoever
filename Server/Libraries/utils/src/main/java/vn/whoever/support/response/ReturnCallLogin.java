package vn.whoever.support.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Genders;
import vn.whoever.support.model.utils.Privacies;

@XmlRootElement(name = "returnCallLogin")
@XmlType(propOrder = { "avatarPhoto", "coverPhoto", "nickName", "langName", 
		"birthday", "gender", "mobile", "email", "isOnline", "privacy" })
@JsonPropertyOrder(value = { "avatarPhoto", "coverPhoto", "nickName", "langName", 
		"birthday", "gender", "mobile", "email", "isOnline", "privacy" })
public class ReturnCallLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "avatarPhoto")
	private String avatarPhoto;

	@XmlElement(name = "coverPhoto")
	private String coverPhoto;

	@XmlElement(name = "nickName")
	private String nickName;

	@XmlElement(name = "langName")
	private String langName;

	@XmlElement(name = "birthday")
	private String birthday;

	@XmlElement(name = "gender")
	private Genders gender = Genders.unknown;

	@XmlElement(name = "mobile")
	private String mobile;

	@XmlElement(name = "email")
	private String email;

	@XmlElement(name = "isOnline")
	private Boolean isOnline;

	@XmlElement(name = "privacy")
	private Privacies privacy = Privacies.normal;

	public ReturnCallLogin() {
		super();
	}

	public ReturnCallLogin(String langName, String nickName, String birthday, Genders gender, String mobile,
			String email, Boolean isOnline, Privacies privacy, String avatarPhoto, String coverPhoto) {
		super();
		this.langName = langName;
		this.nickName = nickName;
		this.birthday = birthday;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.isOnline = isOnline;
		this.privacy = privacy;
		this.avatarPhoto = avatarPhoto;
		this.coverPhoto = coverPhoto;
	}

	public String getAvatarPhoto() {
		return avatarPhoto;
	}

	public void setAvatarPhoto(String avatarPhoto) {
		this.avatarPhoto = avatarPhoto;
	}

	public String getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
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
