package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.ProfilesDao;
import vn.whoever.mainserver.model.Profiles;
/**
 * @author Nguyen Van Do
 *	
 *	This class provide accessing to database that concern about profile of user.
 */
@Repository("profileDao")
public class ProfilesDaoImpl extends AbstractDao<String, Profiles> implements ProfilesDao, Serializable {

	private static final long serialVersionUID = 19947384934L;

	public Profiles getProfiles(String idUser) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idUser", idUser));
		return (Profiles) crit.uniqueResult();
	}

	public void updateProfiles(Profiles profiles) {
		update(profiles);
	}

	public void setProfiles(Profiles profiles) {
		persist(profiles);
	}

	public String getIdUser(String idProfile) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idProfile", idProfile));
		return ((Profiles) crit.uniqueResult()).getIdUser();
	}

	public String getIdProfile(String idUser) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idUser", idUser));
		return ((Profiles) crit.uniqueResult()).getIdProfile();
	}

	public String getNickname(String idUser) {
		String sql = "select nickName from Profiles where idUser = '" + idUser + "'";
		Query query = getSession().createQuery(sql);
		return (String) query.uniqueResult();
	}
	
	// Query user by nickname/username
	@SuppressWarnings("unchecked")
	public List<Profiles> queryIdUserByNickname(String nickName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.like("nickName", nickName, MatchMode.ANYWHERE));
		return (List<Profiles>) crit.list();
	}
}
