package vn.whoever.support.model.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "requestLogin")
@XmlType(propOrder = {"ssoId", "password"})
@JsonPropertyOrder(value = {"ssoId", "password"})
public class RequestLogin {
	
	@XmlElement(name = "ssoId", required = true)
	private String ssoId;
	
	@XmlElement(name = "password", required = true)
	private String password;
	
	public RequestLogin() {
		super();
	}
	
	public RequestLogin(String ssoId, String password) {
		super();
		this.ssoId = ssoId;
		this.password = password;
	}
	
	public String getSsoId() {
		return ssoId;
	}
	
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
