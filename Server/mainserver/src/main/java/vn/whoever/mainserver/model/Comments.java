package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Comments")
public class Comments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 109849757293L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idComment;
	
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name = "idUser")
	private Users users;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idStatus")
	private Status status;
	
	@Column(name = "conntent", nullable = false)
	private String content;
	
	@Column(name = "isUseAccount", nullable = false)
	private Boolean isUseAccount;
	
	public Comments() {
		super();
	}

	public Comments(String idComment, Users users, Status status, String content, Boolean isUseAccount) {
		super();
		this.idComment = idComment;
		this.users = users;
		this.status = status;
		this.content = content;
		this.isUseAccount = isUseAccount;
	}

	public String getIdComment() {
		return idComment;
	}
	
	public void setIdComment(String idComment) {
		this.idComment = idComment;
	}
	
	public Users getUsers() {
		return users;
	}
	
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
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
