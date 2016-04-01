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
@Table(name = "Profiles", uniqueConstraints = @UniqueConstraint(columnNames = { "idUser" }))
public class Profiles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 17074287424L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idProfile;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", nullable = false)
	private Users users;

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

	public Profiles(String idProfile, Users users, String nickName, Date birthday, Genders genders, String mobile,
			String email, Privacies privacy) {
		super();
		this.idProfile = idProfile;
		this.users = users;
		this.nickName = nickName;
		this.birthday = birthday;
		this.genders = genders;
		this.mobile = mobile;
		this.email = email;
		this.privacy = privacy;
	}

	protected String getIdProfile() {
		return idProfile;
	}

	protected void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}

	protected Users getUsers() {
		return users;
	}

	protected void setUsers(Users users) {
		this.users = users;
	}

	protected String getNickName() {
		return nickName;
	}

	protected void setNickName(String nickName) {
		this.nickName = nickName;
	}

	protected Date getBirthday() {
		return birthday;
	}

	protected void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	protected Genders getGenders() {
		return genders;
	}

	protected void setGenders(Genders genders) {
		this.genders = genders;
	}

	protected String getMobile() {
		return mobile;
	}

	protected void setMobile(String mobile) {
		this.mobile = mobile;
	}

	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected Privacies getPrivacy() {
		return privacy;
	}

	protected void setPrivacy(Privacies privacy) {
		this.privacy = privacy;
	}
}
