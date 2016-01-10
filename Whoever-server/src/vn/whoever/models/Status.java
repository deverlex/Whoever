package vn.whoever.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Status {
	
	private int idStatus;
	private String contentStatus;
	//private User senderStatus;
	private Date dateWriterStatus;
	private String imageUpload;
	
	private HashMap<String, Reply> comments; //idUser & Reply
	private ArrayList<String> likes; //idUser
	private ArrayList<String> dislikes; //idUser
	
	public Reply getComment(String idUser) {
		if(this.comments != null) {
			return comments.get(idUser);
		} else {
			return null;
		}
	}
	
	public int getCountLikes() {
		if(this.likes != null) {
			return likes.size();
		} else {
			return 0;
		}
	}
	
	public int getCountDislikes() {
		if(this.dislikes != null) {
			return this.dislikes.size();
		} else {
			return 0;
		}
	}
	
	public int getCountComments() {
		if(this.comments != null) {
			return this.comments.size();
		} else {
			return 0;
		}
	}
}
