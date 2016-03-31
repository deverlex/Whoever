package vn.whoever.support.model.utils;

public enum Privacies {
	
	secret("secret"),
	normal("normal"),
	open("open");

	private String privacy;
	
	private Privacies(final String privacy) {
		this.privacy = privacy;
	}
	
	public String getPrivacies() {
		return this.privacy;
	}
	
	@Override
	public String toString() {
		return this.privacy;
	}
	
	public String getName() {
		return this.getName();
	}
}
