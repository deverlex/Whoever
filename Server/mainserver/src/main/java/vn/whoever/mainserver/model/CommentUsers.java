package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import vn.whoever.support.model.utils.Interacts;

@Entity
@Table(name = "comment_users", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"idUser", "idComment"}))
public class CommentUsers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -165453L;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "idComment")
	private Comments comments;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "idUser")
	private Users users;
	
	@Column(name = "interact", nullable = false)
	@Enumerated(EnumType.STRING)
	private Interacts interacts;
	
	public CommentUsers() {
		super();
	}

	public CommentUsers(Users users, Comments comments, Interacts interacts) {
		super();
		this.users = users;
		this.comments = comments;
		this.interacts = interacts;
	}

	public Users getUsers() {
		return users;
	}
	
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public Comments getComments() {
		return comments;
	}
	
	public void setComments(Comments comments) {
		this.comments = comments;
	}
	
	public Interacts getInteracts() {
		return interacts;
	}
	
	public void setInteracts(Interacts interacts) {
		this.interacts = interacts;
	}
}
