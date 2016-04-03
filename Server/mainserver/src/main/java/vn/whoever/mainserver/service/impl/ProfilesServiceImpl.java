package vn.whoever.mainserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.mainserver.dao.ProfilesDao;
import vn.whoever.mainserver.model.Profiles;

@Service("profilesService")
@Transactional
public class ProfilesServiceImpl implements ProfilesDao {

	@Autowired
	private ProfilesDao dao;
	
	public Profiles getProfiles(String idUser) {
		return null;
	}

	public void updateProfiles(Profiles profiles) {
		
	}

	public void setProfiles(Profiles profiles) {
		dao.setProfiles(profiles);
	}

}
