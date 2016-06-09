package vn.whoever.mainserver.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vn.whoever.mainserver.model.Users;
import vn.whoever.support.model.utils.Roles;
import vn.whoever.support.model.utils.States;

public interface UsersService {

	public void authenticalUser(HttpServletRequest request, HttpSession session, String ssoId, String password);

	public void authByRequest(HttpServletRequest request);

	public String generateUserId();

	public String generateSsoId();

	public String generatePassword();

	public Users findBySsoId(String ssoId);

	public Users findByIdUser(String idUser);

	public String findIdUser(String ssoId);

	public String findSsoIdbyIdUser(String idUser);

	public void registerUser(Users users);

	public void addRole(Users users, Roles roles);

	public void updateState(Users users, States state);

	public void updateTimeUp(String idUser);

	public List<Users> queryIdUserBySsoId(String querySsoId);
}
