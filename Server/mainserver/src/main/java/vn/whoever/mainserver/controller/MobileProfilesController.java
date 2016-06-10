package vn.whoever.mainserver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.model.Profiles;
import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.request.SetProfile;
import vn.whoever.support.model.request.UpdateProfile;
import vn.whoever.support.response.ReturnProfile;

/**
 * @author Nguyen Van Do
 * This file implement function set/get/update profile of users.
 */
@Controller
public class MobileProfilesController {

	@Autowired
	private UsersService userService;

	@Autowired
	private ProfilesService profileService;

	@Autowired
	private AuthToken authToken;

	/**
	 * This method execute when account user created This requesting is describe
	 * below
	 *
	 * { "nickName" : "", "birthday" : "", "gender" : "", "mobile" : "", "email"
	 * : "", "privacy" : "" }
	 */
	@RequestMapping(value = {"/mobile/profiles" }, method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody Boolean setProfile(HttpServletRequest request, @RequestBody SetProfile setProfile) {

		String idUser = authToken.getIdUserHttp(request);
		String idProfile = profileService.generateIdProfile();

		Profiles profile = new Profiles(idProfile, idUser, setProfile.getNickName(), setProfile.getBirthday(),
				setProfile.getGender(), setProfile.getMobile(), setProfile.getEmail(), setProfile.getPrivacy());
		// return result of inserting info to Database
		try {
			profileService.setProfile(profile);
			return true;
		} catch (Exception e)  {}
		return false;
	}

	/***
	 * This method implement get profile function.
	 * 
	 * Message responsive is describe below { "nickName" : "", "birthday" : "",
	 * "gender" : "", "mobile" : "", "email" : "", "privacy" : "" }
	 */
	@RequestMapping(value = { "/mobile/profiles/{ssoId}" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ReturnProfile getProfile(@PathVariable("ssoId") String ssoId) {

		String idUser = userService.findIdUser(ssoId);
		// get profile by idUser
		Profiles profile = profileService.getProfile(idUser);
		return (new ReturnProfile(profile.getNickName(), profile.getBirthday(), profile.getGenders(),
				profile.getMobile(), profile.getEmail(), profile.getPrivacy()));
	}

	/**
	 * This method describe update profile user's function
	 * 
	 * Requesting message is describing below { "nickName" : "", "birthday" :
	 * "", "gender" : "", "mobile" : "", "privacy" : "" }
	 */
	@RequestMapping(value = {"/mobile/profiles/{ssoId}"}, method = RequestMethod.PUT, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody Boolean updateProfile(@PathVariable("ssoId") String ssoId,
			@RequestBody UpdateProfile upProfile) {
		String idUser = userService.findIdUser(ssoId);
		Profiles old = profileService.getProfile(idUser);

		if (upProfile.getNickName() == null) {
			upProfile.setNickName(old.getNickName());
		}
		if (upProfile.getBirthday() == null) {
			upProfile.setBirthday(old.getBirthday());
		}
		if (upProfile.getGender() == null) {
			upProfile.setGender(old.getGenders());
		}
		if (upProfile.getMobile() == null) {
			upProfile.setMobile(old.getMobile());
		}
		if (upProfile.getEmail() == null) {
			upProfile.setEmail(old.getEmail());
		}
		if (upProfile.getPrivacy() == null) {
			upProfile.setPrivacy(old.getPrivacy());
		}

		Profiles profile = new Profiles(old.getIdProfile(), idUser, upProfile.getNickName(), upProfile.getBirthday(),
				upProfile.getGender(), upProfile.getMobile(), upProfile.getEmail(), upProfile.getPrivacy());

		// returning is result of insert info into database
		try {
			profileService.updateProfile(profile);
			return true;
		} catch (Exception e) {	}
		return false;
	}
}
