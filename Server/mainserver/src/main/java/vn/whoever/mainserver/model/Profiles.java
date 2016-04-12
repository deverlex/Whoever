package vn.whoever.mainserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import vn.whoever.support.model.utils.Genders;
import vn.whoever.support.model.utils.Privacies;

@Entity
@Table(name = "Profiles", uniqueConstraints = 
		@UniqueConstraint(columnNames = { "idUser" }))
public class Profiles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 17074287424L;

	@Id
	private String idProfile;

	@Column(name = "idUser", length = 16, nullable = false)
	private String idUser;

	@Column(name = "nickName", length = 32)
	private String nickName;

	@Column(name = "birthday", nullable = false)
	private Date birthday;

	@Column(name = "gender", length = 12, nullable = false)
	@Enumerated(EnumType.STRING)
	private Genders genders = Genders.unknown;

	@Column(name = "mobile", length = 16)
	private String mobile;

	@Column(name = "email", length = 64)
	private String email;

	@Column(name = "privacy", length = 12)
	@Enumerated(EnumType.STRING)
	private Privacies privacy = Privacies.normal;

	public Profiles() {
		super();
	}

	public Profiles(String idProfile, String idUser, String nickName, Date birthday) {
		super();
		this.idProfile = idProfile;
		this.idUser = idUser;
		this.nickName = nickName;
		this.birthday = birthday;
	}

	public Profiles(String idProfile, String idUser, String nickName, Date birthday, Genders genders, String mobile,
			String email, Privacies privacy) {
		super();
		this.idProfile = idProfile;
		this.idUser = idUser;
		this.nickName = nickName;
		this.birthday = birthday;
		this.genders = genders;
		this.mobile = mobile;
		this.email = email;
		this.privacy = privacy;
	}

	public String getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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

	public Genders getGenders() {
		return genders;
	}

	public void setGenders(Genders genders) {
		this.genders = genders;
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
