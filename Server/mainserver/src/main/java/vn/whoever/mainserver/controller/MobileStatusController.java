package vn.whoever.mainserver.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.model.Status;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.mainserver.service.LocationIPService;
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.mainserver.service.StatusService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.mainserver.service.utils.ClientLocation;
import vn.whoever.support.model.request.GetStatus;
import vn.whoever.support.model.request.UserInteract;
import vn.whoever.support.model.request.PostStatus;
import vn.whoever.support.model.utils.Interacts;
import vn.whoever.support.response.ReturnStatus;
import vn.whoever.support.utils.TimePost;

/**
 * return 10 item status for a request get status
 * 
 * @author spider man
 *
 */

@Controller
public class MobileStatusController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private ProfilesService profileService;
	
	@Autowired
	private LocationIPService locationService;
	
	@Autowired
	private AuthToken authToken;
	
	@RequestMapping(value = "/mobile/home/{langCode}", method = RequestMethod.GET,
			produces = "application/json")
	public @ResponseBody List<ReturnStatus> getHome(HttpServletResponse httpResponse,
			@PathVariable("langCode") String langCode) {
		List<ReturnStatus> listReturn = new ArrayList<ReturnStatus>();
		
		return listReturn;
	}
	
	@RequestMapping(value = "/mobile/news", method = RequestMethod.POST, 
			consumes = "application/json" ,produces = "application/json")
	public @ResponseBody List<ReturnStatus> getNews(HttpServletRequest request, HttpServletResponse response,
			@RequestBody GetStatus getStatus) {
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if(ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		ClientLocation location = locationService.getLocation(ipAddress);
		
		List<ReturnStatus> listReturn = new ArrayList<ReturnStatus>();
		String idUser = authToken.getIdUserHttp(request);
		List<Status> listTemp = statusService.getListStatus(idUser, getStatus.getOrder(), 
				getStatus.getOffset(), location.getLatitude(), location.getLongitude());
		
		for (Status status : listTemp) {
			ReturnStatus rStatus = new ReturnStatus();
			rStatus.setIdStatus(status.getIdStatus());
			if(status.getIsUseAccount()) {
				String ssoIdPoster = userService.findSsoIdbyIdUser(status.getIdUser());
				rStatus.setSsoIdPoster(ssoIdPoster);
				rStatus.setAvatarPoster(null);
				String nickName = profileService.getNickName(status.getIdUser());
				rStatus.setNamePoster(nickName);
			} else {
				rStatus.setSsoIdPoster(null);
				rStatus.setAvatarPoster(null);
				rStatus.setNamePoster("anonymous");
			}
			String timePost = TimePost.getTimePost(status.getTimeUp());
			rStatus.setTimePost(timePost);
			rStatus.setContentText(status.getContent());
			rStatus.setContentImage(null);
			
			rStatus.setTotalLike(statusService.getTotalLikes(status.getIdStatus()));
			rStatus.setTotalDislike(statusService.getTotalDislikes(status.getIdStatus()));
			rStatus.setTotalComment(statusService.getTotalComments(status.getIdStatus()));
			
			rStatus.setInteract(statusService.getInteractStatusState(status.getIdStatus(), idUser));
			listReturn.add(rStatus);
		}
		return listReturn;
	}
	
	@RequestMapping(value = "/mobile/status", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String postStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PostStatus postStatus) {
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if(ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		ClientLocation location = locationService.getLocation(ipAddress);
		
		Boolean hasImage = postStatus.getContentImage().equals("") ? false : true;
		
		Status status = new Status(statusService.generateStatusId(), authToken.getIdUserHttp(request), 
				postStatus.getContentText(), new Date(), location.getLatitude(), location.getLongitude(), 
				postStatus.getPrivacy(), postStatus.getIsUseAccount(), hasImage);
		
		if(hasImage) {
			//TODO: insert image to DB in here
			
		}
		statusService.postStatus(status);
		return "200_OK"; 
	}

	@RequestMapping(value = "/mobile/status/{idStatus}", method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody void userInteract(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "idStatus") String idStatus,
			@RequestBody UserInteract interact) {
		if(interact.getInteract().equals(Interacts.like) ||
				interact.getInteract().equals(Interacts.dislike)) {
			statusService.statusInteract(idStatus, authToken.getIdUserHttp(request), interact);
		} else {
			try {
				/**
				 * Notify: don't have this content
				 * 
				 */
				response.sendError(HttpServletResponse.SC_NO_CONTENT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
