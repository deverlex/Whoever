package vn.whoever.mainserver.service;

import vn.whoever.mainserver.model.Profiles;

public interface ProfilesService {
	
	public void updateProfile(Profiles profile);
	public void setProfile(Profiles profile);
	public Profiles getProfile(String ssoId);
}
