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
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.mainserver.service.StatusService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.request.GetStatus;
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
	
	@RequestMapping(value = "/mobile/home/{langCode}", method = RequestMethod.GET,
			produces = "application/json")
	public @ResponseBody String getHome(HttpServletResponse httpResponse,
			@PathVariable("langCode") String langCode) {
		
		return "";
	}
	
	@RequestMapping(value = "/mobile/news", method = RequestMethod.POST, 
			consumes = "application/json" ,produces = "application/json")
	public @ResponseBody List<ReturnStatus> getNews(HttpServletRequest request, HttpServletResponse response,
			@RequestBody GetStatus getStatus) {
		List<ReturnStatus> listReturn = new ArrayList<ReturnStatus>();
		List<Status> listTemp = statusService.getListStatus(getStatus);
		
		for (Status status : listTemp) {
			ReturnStatus rStatus = new ReturnStatus();
			rStatus.setIdStatus(status.getIdStatus());
			if(status.getIsUseAccount()) {
				String ssoIdPoster = userService.findSsoIdbyIdUser(status.getIdUser());
				rStatus.setSsoIdPoster(ssoIdPoster);
				// set avatar = null, develop later
				rStatus.setAvatarPoster(null);
				String nickName = profileService.getNickName(status.getIdUser());
				rStatus.setNamePoster(nickName);
			} else {
				rStatus.setSsoIdPoster("null");
				rStatus.setAvatarPoster("null");
				rStatus.setNamePoster("anonymous");
			}
			String timePost = TimePost.getTimePost(status.getTimePost());
			rStatus.setTimePost(timePost);
			rStatus.setContentText(status.getContent());
			rStatus.setContentImage(null);
			rStatus.setTotalLike(0);
			rStatus.setTotalDislike(10);
			rStatus.setTotalComment(5);
			listReturn.add(rStatus);
		}
		return listReturn;
	}
	
	@RequestMapping(value = "/mobile/status", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String postStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PostStatus postStatus) {
		
		Boolean hasImage = postStatus.getContentImage().equals("") ? false : true;
		Users users = userService.findBySsoId(postStatus.getSsoId());
		
		Status status = new Status(statusService.generateStatusId(), users.getIdUser(), postStatus.getContentText(),
				new Date(), postStatus.getLocation().getxLoc(), postStatus.getLocation().getyLoc(), 
				postStatus.getPrivacy(), postStatus.getIsUseAccount(), hasImage);
		boolean result = statusService.postStatus(status);
		if(hasImage) {
			//TODO: insert image to DB in here
			
		}
		if(result == true){
			return "Post Successfull!!";
		}
		return "post fail";
	}

	@RequestMapping(value = "/mobile/status/{idStatus}", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public void interactStatus(HttpServletResponse response, @PathVariable(value = "idStatus") String idStatus,
			@RequestParam(value = "action") String action) {
		if(action.equals(Interacts.like)) {
			
		} else if(action.equals(Interacts.dislike)) {
			
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
