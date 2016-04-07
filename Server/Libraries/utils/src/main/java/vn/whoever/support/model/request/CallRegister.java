package vn.whoever.support.model.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Location;

@XmlRootElement
@XmlType(propOrder = {"ssoId", "password", "nickName", "birthday", "langCode", "location"})
@JsonPropertyOrder(value = {"ssoId", "password", "nickName", "birthday", "langCode", "location"})
public class CallRegister {
	
	@XmlElement(name = "ssoId", required = true)
	private String ssoId;
	
	@XmlElement(name = "password", required = true)
	private String password;
	
	@XmlElement(name = "nickName")
	private String nickName;
	
	@XmlElement(name = "birthday")
	private Date birthday;
	
	@XmlElement(name = "langCode", required = true)
	private String langCode;
	
	@XmlElement(name = "location")
	private List<Location> locations = new ArrayList<Location>();
	
	public CallRegister() {
		super();
	}

	public CallRegister(String ssoId, String password, String nickName, Date birthday, 
			String langCode, List<Location> locations) {
		super();
		this.ssoId = ssoId;
		this.password = password;
		this.nickName = nickName;
		this.birthday = birthday;
		this.langCode = langCode;
		this.locations = locations;
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
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
}
