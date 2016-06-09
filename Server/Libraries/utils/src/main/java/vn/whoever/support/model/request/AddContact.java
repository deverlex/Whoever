package vn.whoever.support.model.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@XmlRootElement(name = "addContact")
@XmlType(propOrder = { "ssoIdFriend" })
@JsonPropertyOrder(value = { "ssoIdFriend" })
public class AddContact implements Serializable {
	
	private static final long serialVersionUID = -8962553222971L;

	@XmlElement(name = "ssoIdFriend", required = true)
	private String ssoIdFriend;

	public AddContact() {
		super();
	}

	public AddContact(String ssoIdFriend) {
		super();
		this.ssoIdFriend = ssoIdFriend;
	}

	public String getSsoIdFriend() {
		return ssoIdFriend;
	}

	public void setSsoIdFriend(String ssoIdFriend) {
		this.ssoIdFriend = ssoIdFriend;
	}
}
