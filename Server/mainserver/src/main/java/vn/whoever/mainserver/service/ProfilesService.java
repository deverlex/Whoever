package vn.whoever.mainserver.service;

import java.util.List;

import vn.whoever.mainserver.model.Profiles;

public interface ProfilesService {
	
	public String generateIdProfile();
	public boolean updateProfile(Profiles profile);
	public boolean setProfile(Profiles profile);
	public Profiles getProfile(String idUser);
	public String getIdUser(String idProfile);
	public String getIdProfile(String idUser);
	
	public String getNickName(String idUser);
}
