package vn.whoever.support.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@XmlRootElement(name = "returnContact")
@XmlType(propOrder = {"ssoId", "nickName", "latestOnline"})
@JsonPropertyOrder(value = {"ssoId", "nickName", "latestOnline"})
public class ReturnContact {

	@XmlElement(name = "ssoId")
	private String ssoId;
	
	@XmlElement(name = "nickName")
	private String nickName;
	
	@XmlElement(name = "latestOnline")
	private String latestOnline;
	
	public ReturnContact() {
		super();
	}

	public ReturnContact(String ssoId, String nickName, String latestOnline) {
		super();
		this.ssoId = ssoId;
		this.nickName = nickName;
		this.latestOnline = latestOnline;
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
}
