package vn.whoever.mainserver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.mainserver.dao.ProfilesDao;
import vn.whoever.mainserver.model.Profiles;
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.service.impl.GenerateIdImpl;

/**
 * @author Nguyen Van Do
 * 
 *	This class provide functions for create/get/update info profile of user
 */
@Service("profilesService")
@Transactional
public class ProfilesServiceImpl implements ProfilesService {

	@Autowired
	private ProfilesDao profilesDao;

	public String generateIdProfile() {
		return GenerateIdImpl.generateId().getId();
	}

	public void updateProfile(Profiles profile) {
		profilesDao.updateProfiles(profile);
	}

	public void setProfile(Profiles profile) {
		profilesDao.setProfiles(profile);
	}

	public Profiles getProfile(String idUser) {
		return profilesDao.getProfiles(idUser);
	}

	public String getIdUser(String idProfile) {
		return profilesDao.getIdUser(idProfile);
	}

	public String getIdProfile(String idUser) {
		return profilesDao.getIdProfile(idUser);
	}

	public String getNickName(String idUser) {
		return profilesDao.getNickname(idUser);
	}
	
	/**
	 * Get list account when user search contact
	 */
	public List<Profiles> queryIdUserByNickName(String queryNickName) {
		return profilesDao.queryIdUserByNickname(queryNickName);
	}
}
