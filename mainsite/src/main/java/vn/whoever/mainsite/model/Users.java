package vn.whoever.mainsite.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	private String ssoId;
	private String email;
	private String password;
	private Date birthday;
	
	private UserStates states;
	private Set<UserRoles> roles = new HashSet<UserRoles>();
	private Languages language;
	
	public Users() {
		super();
	}
	
	public Users(String ssoId, String email, String password, Date birthday, 
			UserStates states, Set<UserRoles> roles, Languages language) {
		super();
		this.ssoId = ssoId;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.states = states;
		this.roles = roles;
		this.language = language;
	}

	@Id
	@Column(name = "ssoId", length = 32)
	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	@Column(name = "email", length = 64)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password", length = 32)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "birthday")
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@ManyToOne
	@JoinColumn(name = "langCode", nullable = false)
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
