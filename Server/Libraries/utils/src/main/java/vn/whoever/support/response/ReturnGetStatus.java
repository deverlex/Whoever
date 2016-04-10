package vn.whoever.support.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "returnGetStatus")
@XmlType(propOrder = {"idStatus", "idPoster", "namePoster", "timePost", "contentText", "contentImage", 
					"totalLike", "totalDislike", "totalComment"})
@JsonPropertyOrder(value = {"idStatus", "idPoster", "namePoster", "timePost", "contentText", "contentImage", 
					"totalLike", "totalDislike", "totalComment"})
public class ReturnGetStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1993263526373823L;

	@XmlElement(name = "idStatus", required = true)
	private String idStatus;
	
	@XmlElement(name = "ssoIdPoster", required = true)
	private String ssoIdPoster;
	
	@XmlElement(name = "avatarPoster")
	private String avatarPoster;
	
	@XmlElement(name = "namePoster")
	private String namePoster;
	
	@XmlElement(name = "timePost", required = true)
	private String timePost;
	
	@XmlElement(name = "contentText")
	private String contentText;
	
	@XmlElement(name = "contentImage")
	private String contentImage;
	
	@XmlElement(name = "totalLike", required = true)
	private int totalLike;
	
	@XmlElement(name = "totalDislike", required = true)
	private int totalDislike;
	
	@XmlElement(name = "totalComment", required = true)
	private int totalComment;

	public ReturnGetStatus() {
		super();
	}

	public ReturnGetStatus(String idStatus, String ssoIdPoster, String avatarPoster, String namePoster, String timePost,
			String contentText, String contentImage, int totalLike, int totalDislike, int totalComment) {
		super();
		this.idStatus = idStatus;
		this.ssoIdPoster = ssoIdPoster;
		this.avatarPoster = avatarPoster;
		this.namePoster = namePoster;
		this.timePost = timePost;
		this.contentText = contentText;
		this.contentImage = contentImage;
		this.totalLike = totalLike;
		this.totalDislike = totalDislike;
		this.totalComment = totalComment;
	}

	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public String getSsoIdPoster() {
		return ssoIdPoster;
	}

	public void setSsoIdPoster(String ssoIdPoster) {
		this.ssoIdPoster = ssoIdPoster;
	}

	public String getAvatarPoster() {
		return avatarPoster;
	}

	public void setAvatarPoster(String avatarPoster) {
		this.avatarPoster = avatarPoster;
	}

	public String getNamePoster() {
		return namePoster;
	}

	public void setNamePoster(String namePoster) {
		this.namePoster = namePoster;
	}

	public String getTimePost() {
		return timePost;
	}

	public void setTimePost(String timePost) {
		this.timePost = timePost;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getContentImage() {
		return contentImage;
	}

	public void setContentImage(String contentImage) {
		this.contentImage = contentImage;
	}

	public int getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(int totalLike) {
		this.totalLike = totalLike;
	}

	public int getTotalDislike() {
		return totalDislike;
	}

	public void setTotalDislike(int totalDislike) {
		this.totalDislike = totalDislike;
	}

	public int getTotalComment() {
		return totalComment;
	}

	public void setTotalComment(int totalComment) {
		this.totalComment = totalComment;
	}
}
