package vn.whoever.mainserver.service;

import vn.whoever.mainserver.model.Languages;

public interface LanguageService {
	
	public Languages findByCode(String code);
}
