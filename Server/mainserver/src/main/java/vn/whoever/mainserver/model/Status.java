package vn.whoever.mainserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.whoever.support.model.utils.Privacies;

@Entity
@Table(name = "Status")
public class Status implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -155579065L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idUser", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT))
	private Users users;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "timePost", nullable = false)
	private Date timePost;
	
	@Column(name = "xLoc", nullable = false)
	private Double xLoc;
	
	@Column(name = "yLoc", nullable = false)
	private Double yLoc;
	
	@Column(name = "privacy", nullable = false)
	@Enumerated(EnumType.STRING)
	private Privacies privacies = Privacies.normal;
	
	@Column(name = "isUseAccount", nullable = false)
	private Boolean isUseAccount;
	
	public Status() {
		super();
	}

	public Status(String idStatus, Users users, String content, Date timePost, Double xLoc, Double yLoc,
			Privacies privacies, Boolean isUseAccount) {
		super();
		this.idStatus = idStatus;
		this.users = users;
		this.content = content;
		this.timePost = timePost;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.privacies = privacies;
		this.isUseAccount = isUseAccount;
	}

	public String getIdStatus() {
		return idStatus;
	}
	
	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}
	
	public Users getUsers() {
		return users;
	}
	
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getTimePost() {
		return timePost;
	}
	
	public void setTimePost(Date timePost) {
		this.timePost = timePost;
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
	
	public Privacies getPrivacies() {
		return privacies;
	}
	
	public void setPrivacies(Privacies privacies) {
		this.privacies = privacies;
	}
	
	public Boolean getIsUseAccount() {
		return isUseAccount;
	}
	
	public void setIsUseAccount(Boolean isUseAccount) {
		this.isUseAccount = isUseAccount;
	}
}
