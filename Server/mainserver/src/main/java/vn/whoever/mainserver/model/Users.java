package vn.whoever.mainserver.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import vn.whoever.mainserver.model.supports.Genders;

@Entity
@Table(name = "Users", uniqueConstraints = 
	{@UniqueConstraint(columnNames = {"ssoId"}), 
			@UniqueConstraint(columnNames = {"email"})})
public class Users implements Serializable {

	private static final long serialVersionUID = 167574463L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser", nullable = false)
	private long idUser;
	
	@Column(name = "ssoId", length = 32, nullable = false)
	private String ssoId;
	
	@Column(name = "password", length = 32)
	private String password;
	
	@Column(name = "isAnonymous", nullable = false)
	private boolean isAnonymous;
	
	@Column(name = "email", length = 64)
	private String email;
	
	@Column(name = "mobile", length = 32)
	private String mobile;
	
	@Column(name = "nickName", length = 64)
	private String nickName;
	
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "sex", length = 32)
	private String sex = Genders.UNKNOWN.getGender();
	
	@Column(name = "isGetAroundStatus", nullable = false)
	private boolean isGetAroundStatus;
	
	@Column(name = "isShowOnline", nullable = false)
	private boolean isShowOnline;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idLanguage")
	private Languages language;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserStates states;
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<UserRoles> roles;
	
	public Users() {
		super();
	}
	
	
	
	public Users(long idUser, String ssoId, String password, boolean isAnonymous, String nickName, Languages language,
			UserStates states, List<UserRoles> roles) {
		super();
		this.idUser = idUser;
		this.ssoId = ssoId;
		this.password = password;
		this.isAnonymous = isAnonymous;
		this.nickName = nickName;
		this.language = language;
		this.states = states;
		this.roles = roles;
	}



	public long getIdUser() {
		return idUser;
	}
	
	public void setIdUser(long idUser) {
		this.idUser = idUser;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
	
	public boolean isAnonymous() {
		return isAnonymous;
	}

	public void setAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean isGetAroundStatus() {
		return isGetAroundStatus;
	}

	public void setGetAroundStatus(boolean isGetAroundStatus) {
		this.isGetAroundStatus = isGetAroundStatus;
	}

	public boolean isShowOnline() {
		return isShowOnline;
	}

	public void setShowOnline(boolean isShowOnline) {
		this.isShowOnline = isShowOnline;
	}

	public Languages getLanguage() {
		return language;
	}

	public void setLanguage(Languages language) {
		this.language = language;
	}

	public UserStates getStates() {
		return states;
	}

	public void setStates(UserStates states) {
		this.states = states;
	}

	public List<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoles> roles) {
		this.roles = roles;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Users))
			return false;
		Users other = (Users) obj;
		if (idUser != other.idUser)
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + idUser + ", ssoId=" + ssoId + ", password=" + password
				+ ", email=" + email + ", nickName=" + nickName + ", birthday=" + birthday
				+ ", language=" + language + ", state=" + states
				+ ", roles=" + roles +"]";
	}
}
