package vn.whoever.support.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "returnSearchContact")
@XmlType(propOrder = {"ssoId", "nickName", "avatar", "isFriend"})
@JsonPropertyOrder(value = {"ssoId", "nickName", "avatar", "isFiend"})
public class ReturnSearchContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 14309420730173L;
	
	@XmlElement(name = "ssoId")
	private String ssoId;
	
	@XmlElement(name = "nickName")
	private String nickName;
	
	@XmlElement(name = "avatar")
	private String avatart;
	
	@XmlElement(name = "isFriend")
	private Boolean isFriend;
	
	public ReturnSearchContact() {
		super();
	}

	public ReturnSearchContact(String ssoId, String nickName, String avatart, Boolean isFriend) {
		super();
		this.ssoId = ssoId;
		this.nickName = nickName;
		this.avatart = avatart;
		this.isFriend = isFriend;
	}

	public Boolean getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(Boolean isFriend) {
		this.isFriend = isFriend;
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
	
	public String getAvatart() {
		return avatart;
	}
	
	public void setAvatart(String avatart) {
		this.avatart = avatart;
	}
}
