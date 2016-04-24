package vn.whoever.support.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "returnSearchContact")
@XmlType(propOrder = {"ssoId", "nickName", "avatar"})
@JsonPropertyOrder(value = {"ssoId", "nickName", "avatar"})
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
	
	public ReturnSearchContact() {
		super();
	}

	public ReturnSearchContact(String ssoId, String nickName, String avatart) {
		super();
		this.ssoId = ssoId;
		this.nickName = nickName;
		this.avatart = avatart;
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
