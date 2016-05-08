package vn.whoever.support.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Interacts;

@XmlRootElement(name = "returnComment")
@XmlType(propOrder = {"idComment", "ssoIdPoster", "namePoster", "avatarPoster", "content", "timePost", "totalLike", "totalDislike"})
@JsonPropertyOrder(value = {"idComment", "ssoIdPoster", "namePoster", "avatarPoster", "content", "timePost", "totalLike", "totalDislike"})
public class ReturnComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1484837783364L;

	@XmlElement(name = "idComment", required = true)
	private String idComment;
	
	@XmlElement(name = "ssoIdPoster")
	private String ssoIdPoster;
	
	@XmlElement(name = "namePoster")
	private String namePoster;
	
	@XmlElement(name = "avatarPoster")
	private String avatarPoster;
	
	@XmlElement(name = "content", required = true)
	private String content;
	
	@XmlElement(name = "timePost", required = true)
	private String timePost;
	
	@XmlElement(name = "totalLike")
	private Integer totalLike;
	
	@XmlElement(name = "totalDislike")
	private Integer totalDislike;
	
	@XmlElement(name = "interact", defaultValue = "normal")
	private Interacts interact;

	public ReturnComment() {
		super();
	}

	public ReturnComment(String idComment, String ssoIdPoster, String namePoster, String avatarPoster, String content,
			String timePost, Integer totalLike, Integer totalDislike, Interacts interact) {
		super();
		this.idComment = idComment;
		this.ssoIdPoster = ssoIdPoster;
		this.namePoster = namePoster;
		this.avatarPoster = avatarPoster;
		this.content = content;
		this.timePost = timePost;
		this.totalLike = totalLike;
		this.totalDislike = totalDislike;
		this.interact = interact;
	}

	public String getIdComment() {
		return idComment;
	}

	public void setIdComment(String idComment) {
		this.idComment = idComment;
	}

	public String getSsoIdPoster() {
		return ssoIdPoster;
	}

	public void setSsoIdPoster(String ssoIdPoster) {
		this.ssoIdPoster = ssoIdPoster;
	}

	public String getNamePoster() {
		return namePoster;
	}

	public void setNamePoster(String namePoster) {
		this.namePoster = namePoster;
	}

	public String getAvatarPoster() {
		return avatarPoster;
	}

	public void setAvatarPoster(String avatarPoster) {
		this.avatarPoster = avatarPoster;
	}

	public String getContent() {
		return content;
	}

	public String getTimePost() {
		return timePost;
	}

	public void setTimePost(String timePost) {
		this.timePost = timePost;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(Integer totalLike) {
		this.totalLike = totalLike;
	}

	public Integer getTotalDislike() {
		return totalDislike;
	}

	public void setTotalDislike(Integer totalDislike) {
		this.totalDislike = totalDislike;
	}

	public Interacts getInteract() {
		return interact;
	}

	public void setInteract(Interacts interact) {
		this.interact = interact;
	}
}