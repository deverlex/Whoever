package vn.whoever.mainserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.model.Profiles;
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.request.SetProfile;
import vn.whoever.support.model.request.UpdateProfile;

@Controller
public class MobileProfilesController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private ProfilesService profileService;

	@RequestMapping(value = {"/mobile/profiles"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody Boolean setProfile(@RequestBody SetProfile setProfile) {
		String idUser = userService.findIdUser(setProfile.getSsoId());
		String idProfile = profileService.generateIdProfile();
		System.out.println("gender: " + setProfile.getGender());
		Profiles profile = new Profiles(idProfile, idUser, setProfile.getNickName(), setProfile.getBirthday(), 
				setProfile.getGender(), setProfile.getMobile(), setProfile.getEmail(), setProfile.getPrivacy());

		return  profileService.setProfile(profile);
	}

	@RequestMapping(value = {"/mobile/profiles/{ssoId}"}, method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody Boolean updateProfile(@PathVariable("ssoId") String ssoId, 
			@RequestBody UpdateProfile upProfile) {
		String idUser = userService.findIdUser(ssoId);
		String idProfile = profileService.getIdProfile(idUser);
		Profiles profile = new Profiles(idProfile, idUser, upProfile.getNickName(), upProfile.getBirthday(), 
				upProfile.getGender(), upProfile.getMobile(), upProfile.getEmail(), upProfile.getPrivacy());
		return profileService.updateProfile(profile);
	}
}
