package vn.whoever.mainserver.service.impl;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.whoever.mainserver.dao.LanguagesDao;
import vn.whoever.mainserver.model.Languages;
import vn.whoever.mainserver.service.LanguagesService;

@Service("languagesService")
@Transactional
public class LanguagesServiceImpl implements LanguagesService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 453535353666771L;
	
	@Autowired
	private LanguagesDao languageDao;
	
	public Languages findByCode(String code) {
		return languageDao.findByCode(code);
	}

	public String findNativeNameById(int idLanguage) {
		return languageDao.findNativeNameById(idLanguage);
	}

	public String findNativeNameByCode(String langCode) {
		return languageDao.findNativeNameByCode(langCode);
	}

	public Integer findIdByCode(String langCode) {
		return languageDao.findIdByCode(langCode);
	}

}
