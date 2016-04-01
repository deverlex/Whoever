package vn.whoever.mainserver.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Comments")
public class Comments {

	private String idComment;
	private Users users;
	private Status status;
	private String content;
	private Boolean isUseAccount;
}
