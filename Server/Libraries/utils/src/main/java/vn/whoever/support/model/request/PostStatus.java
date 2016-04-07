package vn.whoever.support.model.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Location;
import vn.whoever.support.model.utils.Privacies;

@XmlRootElement(name = "status")
@XmlType(propOrder = { "ssoId", "contentText", "contentImage", "privacy", "isUserAccount", "location" })
@JsonPropertyOrder(value = { "ssoId", "contentText", "contentImage", "privacy", "isUserAccount", "location" })
public class PostStatus {

	@XmlElement(name = "ssoId", required = true)
	private String ssoId;

	@XmlElement(name = "contentText")
	private String contentText;

	@XmlElement(name = "contentImage")
	private String contentImage;

	@XmlElement(name = "privacy")
	private Privacies privacy;

	@XmlElement(name = "isUserAccount")
	private Boolean isUserAccount;

	@XmlElement(name = "location")
	private Location location;

	public PostStatus() {
		super();
	}

	public PostStatus(String ssoId, String contentText, String contentImage, Privacies privacy, Boolean isUserAccount,
			Location location) {
		super();
		this.ssoId = ssoId;
		this.contentText = contentText;
		this.contentImage = contentImage;
		this.privacy = privacy;
		this.isUserAccount = isUserAccount;
		this.location = location;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getContentImage() {
		return contentImage;
	}

	public void setContentImage(String contentImage) {
		this.contentImage = contentImage;
	}

	public Privacies getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacies privacy) {
		this.privacy = privacy;
	}

	public Boolean getIsUserAccount() {
		return isUserAccount;
	}

	public void setIsUserAccount(Boolean isUserAccount) {
		this.isUserAccount = isUserAccount;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
