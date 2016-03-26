package vn.whoever.mainserver.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {

	private int idUser;
	private String ssoId;
	private String password;
	private String email;
	private String nickName;
	private Date birthday;
	private Languages language;
	
	private UserStates states;
	private Set<UserRoles> roles = new HashSet<UserRoles>();
	
	public Users() {
		super();
	}

	public Users(int idUser, String ssoId, Languages language, UserStates states, Set<UserRoles> roles) {
		super();
		this.idUser = idUser;
		this.ssoId = ssoId;
		this.language = language;
		this.states = states;
		this.roles = roles;
	}

	public Users(int idUser, String ssoId, String password, String email, String nickName, Languages language,
			UserStates states, Set<UserRoles> roles) {
		super();
		this.idUser = idUser;
		this.ssoId = ssoId;
		this.password = password;
		this.email = email;
		this.nickName = nickName;
		this.language = language;
		this.states = states;
		this.roles = roles;
	}

	public Users(int idUser, String ssoId, String password, String email, Languages language, UserStates states,
			Set<UserRoles> roles) {
		super();
		this.idUser = idUser;
		this.ssoId = ssoId;
		this.password = password;
		this.email = email;
		this.language = language;
		this.states = states;
		this.roles = roles;
	}

	public Users(int idUser, String ssoId, String password, String email, String nickName, Date birthday,
			Languages language, UserStates states, Set<UserRoles> roles) {
		super();
		this.idUser = idUser;
		this.ssoId = ssoId;
		this.password = password;
		this.email = email;
		this.nickName = nickName;
		this.birthday = birthday;
		this.language = language;
		this.states = states;
		this.roles = roles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Column(name = "ssoId", length = 32, unique = true, nullable = false)
	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	@Column(name = "password", length = 32)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "email", length = 64)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "nickName", length = 64)
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Column(name = "birthday")
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	public Languages getLanguage() {
		return language;
	}

	public void setLanguage(Languages language) {
		this.language = language;
	}

	@OneToOne(fetch = FetchType.LAZY)
	public UserStates getStates() {
		return states;
	}

	public void setStates(UserStates states) {
		this.states = states;
	}

	@OneToMany(fetch = FetchType.LAZY)
	public Set<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoles> roles) {
		this.roles = roles;
	}
}
