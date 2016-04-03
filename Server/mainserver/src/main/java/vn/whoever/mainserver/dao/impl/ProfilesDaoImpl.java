package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.ProfilesDao;
import vn.whoever.mainserver.model.Profiles;

@Repository("profileDao")
public class ProfilesDaoImpl extends AbstractDao<String, Profiles> implements ProfilesDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19947384934L;

	public Profiles getProfiles(String idUser) {
	
		return null;
	}

	public void updateProfiles(Profiles profiles) {
		persist(profiles);
	}

	public void setProfiles(Profiles profiles) {
		persist(profiles);
	}

}
