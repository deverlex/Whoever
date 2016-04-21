package vn.whoever.mainserver.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import vn.whoever.support.model.utils.States;

@Entity
@Table(name = "Users", uniqueConstraints = 
	{@UniqueConstraint(columnNames = {"ssoId"})})
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
public class Users implements Serializable {

	private static final long serialVersionUID = 167574463L;

	@Id
	@Column(name = "idUser", nullable = false, length = 16)
	private String idUser;
	
	@Column(name = "ssoId", length = 32, nullable = false)
	private String ssoId;
	
	@Column(name = "password", length = 32)
	private String password;
	
	@Column(name = "state", nullable = false)
	@Enumerated(EnumType.STRING)
	private States state = States.inactive;
	
	@Column(name = "xLoc", nullable = true)
	private Double xLoc;
	
	@Column(name = "yLoc", nullable = true)
	private Double yLoc;
	
	@Column(name = "isAnonymous", nullable = false)
	private Boolean isAnonymous;
	
	@Column(name = "isOnline", nullable = false)
	private Boolean isOnline;
	
	@Column(name = "timeUp")
	private Date timeUp;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "idLanguage")
//	private Languages language;
	
	@Column(name = "idLanguage", nullable = false, length = 4)
	private Integer idLanguage;
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private Set<SetRoles> roles = new HashSet<SetRoles>();
	
	public Users() {
		super();
	}

	public Users(String idUser, String ssoId, String password, States state, Boolean isAnonymous, Boolean isOnline,
			Integer idLanguage) {
		super();
		this.idUser = idUser;
		this.ssoId = ssoId;
		this.password = password;
		this.state = state;
		this.isAnonymous = isAnonymous;
		this.isOnline = isOnline;
		this.idLanguage = idLanguage;
	}

	public Users(String idUser, String ssoId, String password, States state, Double xLoc, Double yLoc,
			Boolean isAnonymous, Boolean isOnline, Integer idLanguage) {
		super();
		this.idUser = idUser;
		this.ssoId = ssoId;
		this.password = password;
		this.state = state;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.isAnonymous = isAnonymous;
		this.isOnline = isOnline;
		this.idLanguage = idLanguage;
	}

	public Users(String idUser, String ssoId, String password, States state, 
				Double xLoc, Double yLoc, Integer idLanguage, Set<SetRoles> roles) {
		super();
		this.idUser = idUser;
		this.ssoId = ssoId;
		this.password = password;
		this.state = state;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.idLanguage = idLanguage;
		this.roles = roles;
	}

	public Users(String idUser, String ssoId, String password, States state, Double xLoc, Double yLoc,
			Boolean isAnonymous, Boolean isOnline, Date timeUp, Integer idLanguage, Set<SetRoles> roles) {
		super();
		this.idUser = idUser;
		this.ssoId = ssoId;
		this.password = password;
		this.state = state;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.isAnonymous = isAnonymous;
		this.isOnline = isOnline;
		this.timeUp = timeUp;
		this.idLanguage = idLanguage;
		this.roles = roles;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
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

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	public Double getxLoc() {
		return xLoc;
	}

	public void setxLoc(Double xLoc) {
		this.xLoc = xLoc;
	}

	public Double getyLoc() {
		return yLoc;
	}

	public void setyLoc(Double yLoc) {
		this.yLoc = yLoc;
	}
	
	public Boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public Boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Date getTimeUp() {
		return timeUp;
	}

	public void setTimeUp(Date timeUp) {
		this.timeUp = timeUp;
	}
	
	public Integer getIdLanguage() {
		return idLanguage;
	}

	public void setIdLanguage(Integer idLanguage) {
		this.idLanguage = idLanguage;
	}

	public Set<SetRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<SetRoles> roles) {
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

//	@Override
//	public String toString() {
//		return "User [id=" + idUser + ", ssoId=" + ssoId + ", password=" + password
//				+ ", state=" + state.getState() + ", xLoc=" + xLoc + ", yLoc=" + yLoc
//				+ ", token=" + token + ", exp_token=" + exp_token
//				+ ", language=" + language + ", roles=[" + roles + "]" + "]";
//	}
}
