package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Languages;

public interface LanguagesDao {

	public Languages findByCode(String langCode);
}
