package vn.whoever.mainserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.model.Profiles;
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.request.SetProfile;

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

	@RequestMapping(value = {"/mobile/profiles/{idProfile}"})
	public Boolean updateProfile(@RequestBody SetProfile setProfile) {
		
		return true;
	}
}
