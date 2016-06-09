package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import vn.whoever.support.model.utils.Interacts;

@Entity
@Table(name = "comment_users", uniqueConstraints = @UniqueConstraint(columnNames = { "idUser", "idComment" }))
@DynamicUpdate(value = true)
public class CommentUsers implements Serializable {

	private static final long serialVersionUID = 99897776165453L;

	@Id
	@Column(name = "idComment", nullable = false)
	private String idComment;

	@Id
	@Column(name = "idUser", nullable = false)
	private String idUser;

	@Column(name = "interact")
	@Enumerated(EnumType.STRING)
	private Interacts interact = Interacts.normal;

	public CommentUsers() {
		super();
	}

	public CommentUsers(String idComment, String idUser, Interacts interact) {
		super();
		this.idComment = idComment;
		this.idUser = idUser;
		this.interact = interact;
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

	public Interacts getInteracts() {
		return interact;
	}

	public void setInteracts(Interacts interacts) {
		this.interact = interacts;
	}
}
