package vn.whoever.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status {
	
	private int idStatus;
	private String contentStatus;
	private User senderStatus;
	private Date dateWriterStatus;
	private String imageUpload;
	private int countComment;
	private int countLike;
	private int countDislike;
	
	private HashMap<String, Reply> comments; //idUser & Reply
	private ArrayList<String> likes; //idUser
	private ArrayList<String> dislikes; //idUser
	
	public Status() {
		comments = new HashMap<>();
		likes = new ArrayList<>();
		dislikes = new ArrayList<>();
	}
		
	public int getIdStatus() {
		return idStatus;
	}

	@XmlElement
	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getContentStatus() {
		return contentStatus;
	}

	@XmlElement
	public void setContentStatus(String contentStatus) {
		this.contentStatus = contentStatus;
	}

	public User getSenderStatus() {
		return senderStatus;
	}

	@XmlElement
	public void setSenderStatus(User senderStatus) {
		this.senderStatus = senderStatus;
	}

	public Date getDateWriterStatus() {
		return dateWriterStatus;
	}

	@XmlElement
	public void setDateWriterStatus(Date dateWriterStatus) {
		this.dateWriterStatus = dateWriterStatus;
	}

	public String getImageUpload() {
		return imageUpload;
	}
	
	@XmlElement
	public void setImageUpload(String imageUpload) {
		this.imageUpload = imageUpload;
	}

	public int getCountComment() {
		return countComment;
	}

	@XmlElement
	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}

	public int getCountLike() {
		return countLike;
	}

	@XmlElement
	public void setCountLike(int countLike) {
		this.countLike = countLike;
	}

	public int getCountDislike() {
		return countDislike;
	}

	@XmlElement
	public void setCountDislike(int countDislike) {
		this.countDislike = countDislike;
	}

	public HashMap<String, Reply> getComments() {
		return comments;
	}
	
	@XmlElement
	public void setComments(HashMap<String, Reply> comments) {
		this.comments = comments;
	}

	public ArrayList<String> getLikes() {
		return likes;
	}

	@XmlElement
	public void setLikes(ArrayList<String> likes) {
		this.likes = likes;
	}
	
	public ArrayList<String> getDislikes() {
		return dislikes;
	}

	@XmlElement
	public void setDislikes(ArrayList<String> dislikes) {
		this.dislikes = dislikes;
	}
	
	/**
	 * TODO: something else
	 */
	
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
