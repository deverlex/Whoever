package vn.whoever.mainsite.model;

public class Languages {

	private String langCode;
	private String standardName;
	private String nativeName;
	private String postalCode;

	public Languages() {
		super();
	}

	public Languages(String langCode, String standardName, String nativeName, String postalCode) {
		super();
		this.langCode = langCode;
		this.standardName = standardName;
		this.nativeName = nativeName;
		this.postalCode = postalCode;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
