package vn.whoever.mainserver.service;

import java.util.List;

import vn.whoever.mainserver.model.Profiles;

public interface ProfilesService {

	public String generateIdProfile();

	public void updateProfile(Profiles profile);

	public void setProfile(Profiles profile);

	public Profiles getProfile(String idUser);

	public String getIdUser(String idProfile);

	public String getIdProfile(String idUser);

	public String getNickName(String idUser);

	public List<Profiles> queryIdUserByNickName(String queryNickName);
}
