package vn.whoever.support.model.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "callLogin")
@XmlType(propOrder = { "ssoId", "password" })
@JsonPropertyOrder(value = { "ssoId", "password" })
public class CallLogin implements Serializable {

	private static final long serialVersionUID = -655678899031L;

	@XmlElement(name = "ssoId", required = true)
	private String ssoId;

	@XmlElement(name = "password", required = true)
	private String password;

	public CallLogin() {
		super();
	}

	public CallLogin(String ssoId, String password) {
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
