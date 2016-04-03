package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.LanguagesDao;
import vn.whoever.mainserver.model.Languages;

@Repository("langDao")
public class LanguagesDaoImpl extends AbstractDao<Integer, Languages> implements LanguagesDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -434665463351L;

	public Languages findByCode(String langCode) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("langCode", langCode));
		return (Languages) crit.uniqueResult();
	}

}
