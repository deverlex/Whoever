package vn.whoever.mainsite.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	@Id
	@Column(name = "langCode", length = 4)
	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	@Column(name = "standarName", length = 32, nullable = false)
	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	@Column(name = "nativeName", length = 64)
	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "postalCode", length = 4)
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
