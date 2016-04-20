package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Profiles;

public interface ProfilesDao {

	public Profiles getProfiles(String idUser);
	public void updateProfiles(Profiles profiles);
	public void setProfiles(Profiles profiles);
	public String getIdUser(String idProfile);
	public String getIdProfile(String idUser);
	
	public String getNickname(String idUser);
}
