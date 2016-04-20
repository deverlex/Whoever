package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Comments")
public class Comments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 109849757293L;

	@Id
	@Column(name = "idComment", nullable = false, length = 16)
	private String idComment;
	
	@Column(name = "idUser", nullable = false, length = 16)
	private String idUser;
	
	@Column(name = "idStatus", nullable = false, length = 16)
	private String idStatus;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "isUseAccount", nullable = false)
	private Boolean isUseAccount;
	
	public Comments() {
		super();
	}

	public Comments(String idComment, String idUser, String idStatus, String content, Boolean isUseAccount) {
		super();
		this.idComment = idComment;
		this.idUser = idUser;
		this.idStatus = idStatus;
		this.content = content;
		this.isUseAccount = isUseAccount;
	}

	public String getIdComment() {
		return idComment;
	}
	
	public void setIdComment(String idComment) {
		this.idComment = idComment;
	}
	
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Boolean getIsUseAccount() {
		return isUseAccount;
	}
	
	public void setIsUseAccount(Boolean isUseAccount) {
		this.isUseAccount = isUseAccount;
	}
}
