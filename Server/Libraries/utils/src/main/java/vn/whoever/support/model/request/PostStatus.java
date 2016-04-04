package vn.whoever.support.model.request;

import vn.whoever.support.model.utils.Location;
import vn.whoever.support.model.utils.Privacies;

public class PostStatus {
	
	private String ssoId;
	private String contentText;
	private String contenntImage;
	private Privacies privacies;
	private Boolean isUserAccount;
	private Location location;
	
	public PostStatus() {
		super();
	}

	public PostStatus(String ssoId, String contentText, String contenntImage, Privacies privacies,
			Boolean isUserAccount, Location location) {
		super();
		this.ssoId = ssoId;
		this.contentText = contentText;
		this.contenntImage = contenntImage;
		this.privacies = privacies;
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
	
	public String getContenntImage() {
		return contenntImage;
	}
	
	public void setContenntImage(String contenntImage) {
		this.contenntImage = contenntImage;
	}
	
	public Privacies getPrivacies() {
		return privacies;
	}
	
	public void setPrivacies(Privacies privacies) {
		this.privacies = privacies;
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
