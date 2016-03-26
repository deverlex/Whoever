package vn.whoever.mainserver.service;

import vn.whoever.mainserver.model.Users;

public interface UserService {

	Users findBySso(String sso);
}
