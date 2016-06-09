package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.LanguagesDao;
import vn.whoever.mainserver.model.Languages;
/**
 * @author Nguyen Van Do
 *	
 *	This class provide accessing to database that concern about languages supporting by system.
 */
@Repository("langDao")
public class LanguagesDaoImpl extends AbstractDao<Integer, Languages> implements LanguagesDao, Serializable {

	private static final long serialVersionUID = -434665463351L;

	public Languages findByCode(String langCode) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("langCode", langCode));
		return (Languages) crit.uniqueResult();
	}

	public Integer findIdByCode(String langCode) {
		String sql = "select idLanguage from Languages where langCode = '" + langCode + "'";
		Query query = getSession().createQuery(sql);
		return (Integer) query.uniqueResult();
	}

	public String findNativeNameByCode(String langCode) {
		String sql = "select nativeName from Languages where langCode = '" + langCode + "'";
		Query query = getSession().createQuery(sql);
		return (String) query.uniqueResult();
	}

	public String findNativeNameById(int idLanguage) {
		String sql = "select nativeName from Languages where idLanguage = " + idLanguage + "";
		Query query = getSession().createQuery(sql);
		return (String) query.uniqueResult();
	}
}
