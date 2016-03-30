package vn.whoever.mainserver.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.whoever.mainserver.dao.LanguagesDao;
import vn.whoever.mainserver.model.Languages;
import vn.whoever.mainserver.service.LanguagesService;

@Service("languagesService")
@Transactional
public class LanguagesServiceImpl implements LanguagesService {

	@Autowired
	private LanguagesDao dao;
	
	public Languages findByCode(String code) {
		return dao.findByCode(code);
	}

}
