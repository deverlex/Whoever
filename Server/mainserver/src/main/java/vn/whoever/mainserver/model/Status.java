package vn.whoever.mainserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import vn.whoever.support.model.utils.Privacies;

@Entity
@Table(name = "Status")
@DynamicInsert(value = true)
public class Status implements Serializable {

	private static final long serialVersionUID = -155579065L;

	@Id
	@Column(name = "idStatus", nullable = false, length = 16)
	private String idStatus;

	@Column(name = "idUser", length = 16, nullable = false)
	private String idUser;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "timePost")
	private Date timePost;

	@Column(name = "xLoc", nullable = false)
	private Double xLoc;

	@Column(name = "yLoc", nullable = false)
	private Double yLoc;

	@Column(name = "privacy", nullable = false)
	@Enumerated(EnumType.STRING)
	private Privacies privacy = Privacies.normal;

	@Column(name = "isUseAccount", nullable = false)
	private Boolean isUseAccount;

	@Column(name = "timeUp")
	private Date timeUp;

	@Column(name = "hasImage", nullable = false)
	private Boolean hasImage;

	public Status() {
		super();
	}

	public Status(String idStatus, String idUser, String content, Date timePost, Double xLoc, Double yLoc,
			Privacies privacy, Boolean isUseAccount, Boolean hasImage) {
		super();
		this.idStatus = idStatus;
		this.idUser = idUser;
		this.content = content;
		this.timePost = timePost;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.privacy = privacy;
		this.isUseAccount = isUseAccount;
		this.hasImage = hasImage;
	}

	public Status(String idStatus, String idUser, String content, Date timePost, Double xLoc, Double yLoc,
			Privacies privacy, Boolean isUseAccount, Date timeUp) {
		super();
		this.idStatus = idStatus;
		this.idUser = idUser;
		this.content = content;
		this.timePost = timePost;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.privacy = privacy;
		this.isUseAccount = isUseAccount;
		this.timeUp = timeUp;
	}

	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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

	public Privacies getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacies privacy) {
		this.privacy = privacy;
	}

	public Boolean getIsUseAccount() {
		return isUseAccount;
	}

	public void setIsUseAccount(Boolean isUseAccount) {
		this.isUseAccount = isUseAccount;
	}

	public Date getTimeUp() {
		return timeUp;
	}

	public void setTimeUp(Date timeUp) {
		this.timeUp = timeUp;
	}

	public Boolean getHasImage() {
		return hasImage;
	}

	public void setHasImage(Boolean hasImage) {
		this.hasImage = hasImage;
	}
}
