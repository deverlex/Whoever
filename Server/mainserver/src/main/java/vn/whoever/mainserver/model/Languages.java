package vn.whoever.mainserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class Languages implements Serializable {

	private static final long serialVersionUID = 15436456456352L;

	@Id
	@GeneratedValue
	private int idLanguage;
	
	@Column(name = "langCode", length = 8, unique = true)
	private String langCode;
	
	@Column(name = "standardName", length = 32, nullable = false)
	private String standardName;
	
	@Column(name = "nativeName", length = 64, nullable = true)
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

	public int getIdLanguage() {
		return idLanguage;
	}

	public void setIdLanguage(int idLanguage) {
		this.idLanguage = idLanguage;
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
}
