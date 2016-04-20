package vn.whoever.support.response;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Interacts;

@XmlRootElement(name = "returnComment")
@XmlType(propOrder = {"idComment", "ssoId", "nickName", "content", "timePost", "totalLike", "totalDislike"})
@JsonPropertyOrder(value = {"idComment", "ssoId", "nickName", "content", "timePost", "totalLike", "totalDislike"})
public class ReturnComment {

	@XmlElement(name = "idComment")
	private String idComment;
	
	@XmlElement(name = "ssoId")
	private String ssoId;
	
	@XmlElement(name = "nickName")
	private String nickName;
	
	@XmlElement(name = "content")
	private String content;
	
	@XmlElement(name = "timePost")
	private Date timePost;
	
	@XmlElement(name = "totalLike", defaultValue = "0")
	private int totalLike;
	
	@XmlElement(name = "totalDislike", defaultValue = "0")
	private int totalDislike;
	
	@XmlElement(name = "interact", defaultValue = "normal")
	private Interacts interact;

	public ReturnComment() {
		super();
	}

	public ReturnComment(String idComment, String ssoId, String nickName, 
						String content, int totalLike, int totalDislike) {
		super();
		this.idComment = idComment;
		this.ssoId = ssoId;
		this.nickName = nickName;
		this.content = content;
		this.totalLike = totalLike;
		this.totalDislike = totalDislike;
	}

	public String getIdComment() {
		return idComment;
	}

	public void setIdComment(String idComment) {
		this.idComment = idComment;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
}