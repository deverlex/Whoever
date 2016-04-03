package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Profiles;

public interface ProfilesDao {

	public Profiles getProfiles(String idUser);
	public void updateProfiles(Profiles profiles);
	public void setProfiles(Profiles profiles);
}
