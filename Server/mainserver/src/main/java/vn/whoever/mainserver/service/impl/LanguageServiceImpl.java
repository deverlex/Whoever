package vn.whoever.mainserver.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.whoever.mainserver.dao.LanguageDao;
import vn.whoever.mainserver.model.Languages;
import vn.whoever.mainserver.service.LanguageService;

@Service("languageService")
@Transactional
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageDao languageDao;
	
	public Languages findByCode(String code) {
		return languageDao.findByCode(code);
	}

}
