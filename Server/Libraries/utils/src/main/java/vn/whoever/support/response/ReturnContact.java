package vn.whoever.support.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@XmlRootElement(name = "returnContact")
@XmlType(propOrder = {"ssoId", "nickName", "latestOnline", "latestStatus"})
@JsonPropertyOrder(value = {"ssoId", "nickName", "latestOnline", "latestStatus"})
public class ReturnContact {

	@XmlElement(name = "ssoId")
	private String ssoId;
	
	@XmlElement(name = "nickName")
	private String nickName;
	
	@XmlElement(name = "latestOnline")
	private String latestOnline;
	
	@XmlElement(name = "latestStatus")
	private String latestStatus;
	
	public ReturnContact() {
		super();
	}

	public ReturnContact(String ssoId, String nickName, String latestOnline, String latestStatus) {
		super();
		this.ssoId = ssoId;
		this.nickName = nickName;
		this.latestOnline = latestOnline;
		this.latestStatus = latestStatus;
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
	
	public String getLatestOnline() {
		return latestOnline;
	}
	
	public void setLatestOnline(String latestOnline) {
		this.latestOnline = latestOnline;
	}
	
	public String getLatestStatus() {
		return latestStatus;
	}
	
	public void setLatestStatus(String latestStatus) {
		this.latestStatus = latestStatus;
	}
}
