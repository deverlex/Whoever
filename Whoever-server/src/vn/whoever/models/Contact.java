package vn.whoever.models;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contact {

	private String idUser;
	private String nickname;
	private String email;
	private String mobile;
	private String avatar;
	
	private ArrayList<Contact> contacts;
	
	public Contact() {
		contacts = new ArrayList<>();
	}

	public String getIdUser() {
		return idUser;
	}

	@XmlElement
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getNickname() {
		return nickname;
	}

	@XmlElement
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	@XmlElement
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAvatar() {
		return avatar;
	}

	@XmlElement
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	@XmlElement
	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	
}
