package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Languages;

public interface LanguagesDao {

	public Languages findByCode(String langCode);
	public Integer findIdByCode(String langCode);
	public String findNativeNameByCode(String langCode);
	public String findNativeNameById(int idLanguage);
}
