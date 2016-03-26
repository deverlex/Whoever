package vn.whoever.mainserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class Languages {

	private int idLanguage;
	private String langCode;
	private String standardName;
	private String nativeName;

	public Languages() {
		super();
	}

	public Languages(int idLanguage, String langCode, String standardName, String nativeName) {
		super();
		this.idLanguage = idLanguage;
		this.langCode = langCode;
		this.standardName = standardName;
		this.nativeName = nativeName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdLanguage() {
		return idLanguage;
	}

	public void setIdLanguage(int idLanguage) {
		this.idLanguage = idLanguage;
	}

	@Column(name = "langCode", length = 8, unique = true)
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
}
