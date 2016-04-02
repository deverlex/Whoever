package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import vn.whoever.support.model.utils.Interacts;

@Entity
@Table(name = "comment_users", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"idUser, idStatus"}))
public class CommentUsers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -165453L;
	
	private Comments comments;
	private Users users;
	private Interacts interacts = Interacts.normal;
	
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
