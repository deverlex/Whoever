package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Languages;

public interface LanguageDao {

	public Languages findByCode(String langCode);
}
