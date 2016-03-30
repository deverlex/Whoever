package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.LanguageDao;
import vn.whoever.mainserver.model.Languages;

@Repository("langDao")
public class LanguageDaoImpl extends AbstractDao<Integer, Languages> implements LanguageDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -434665463351L;

	public Languages findByCode(String langCode) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("langCode", langCode));
		System.out.println("find langCode by code");
		return (Languages) crit.uniqueResult();
	}

}
