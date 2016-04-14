package vn.whoever.mainserver.service;

import vn.whoever.mainserver.model.Languages;

public interface LanguagesService {
	
	public Languages findByCode(String code);
	public String findNativeNameById(int idLanguage);
	public String findNativeNameByCode(String langCode);
	public Integer findIdByCode(String langCode);
}
