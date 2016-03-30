package vn.whoever.mainserver.service;

import vn.whoever.mainserver.model.Languages;

public interface LanguagesService {
	
	public Languages findByCode(String code);
}
