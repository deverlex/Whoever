package vn.whoever.support.model.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addContact")
@XmlType(propOrder = {"ssoId", "ssoIdFriend"})
public class AddContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8962553222971L;

	@XmlElement(name = "ssoId", required = true)
	private String ssoId;
	
	@XmlElement(name = "ssoIdFriend", required = true)
	private String ssoIdFriend;

	public AddContact() {
		super();
	}

	public AddContact(String ssoId, String ssoIdFriend) {
		super();
		this.ssoId = ssoId;
		this.ssoIdFriend = ssoIdFriend;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getSsoIdFriend() {
		return ssoIdFriend;
	}

	public void setSsoIdFriend(String ssoIdFriend) {
		this.ssoIdFriend = ssoIdFriend;
	}

}
