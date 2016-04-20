package vn.whoever.support.model.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.whoever.support.model.utils.Location;
import vn.whoever.support.model.utils.Privacies;

@XmlRootElement(name = "postStatus")
@XmlType(propOrder = { "ssoId", "contentText", "contentImage", "privacy", "isUseAccount", "location" })
@JsonPropertyOrder(value = { "ssoId", "contentText", "contentImage", "privacy", "isUseAccount", "location" })
public class PostStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 900995382611L;

	@XmlElement(name = "ssoId", required = true)
	private String ssoId;

	@XmlElement(name = "contentText")
	private String contentText;

	@XmlElement(name = "contentImage")
	private String contentImage;

	@XmlElement(name = "privacy")
	private Privacies privacy;

	@XmlElement(name = "isUseAccount")
	private Boolean isUseAccount;

	public PostStatus() {
		super();
	}

	public PostStatus(String ssoId, String contentText, String contentImage, Privacies privacy, Boolean isUseAccount) {
		super();
		this.ssoId = ssoId;
		this.contentText = contentText;
		this.contentImage = contentImage;
		this.privacy = privacy;
		this.isUseAccount = isUseAccount;
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

	public Boolean getIsUseAccount() {
		return isUseAccount;
	}

	public void setIsUseAccount(Boolean isUseAccount) {
		this.isUseAccount = isUseAccount;
	}
}
