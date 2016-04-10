package vn.whoever.mainserver.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.model.Status;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.StatusService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.request.GetStatus;
import vn.whoever.support.model.request.PostStatus;
import vn.whoever.support.response.ReturnGetStatus;

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
	
	@RequestMapping(value = "/mobile/gethome/{langCode}", method = RequestMethod.GET,
			produces = "application/json")
	public @ResponseBody String getHome(HttpServletResponse httpResponse,
			@PathVariable("langCode") String langCode) {
		
		return "";
	}
	
	@RequestMapping(value = "/mobile/getnews", method = RequestMethod.POST, 
			consumes = "application/json" ,produces = "application/json")
	public @ResponseBody List<Status> getNews(HttpServletRequest request, HttpServletResponse response,
			@RequestBody GetStatus getStatus) {
		List<ReturnGetStatus> listStatus = new ArrayList<ReturnGetStatus>();
		List<Status> listTemp = statusService.getListStatus(getStatus);
		
		return listTemp;
	}
	
	@RequestMapping(value = "/mobile/post/status", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String postStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PostStatus postStatus) {
		
		Boolean hasImage = postStatus.getContentImage().equals("") ? false : true;
		Users users = userService.findBySsoId(postStatus.getSsoId());
		
		Status status = new Status(statusService.generateStatusId(), users.getIdUser(), postStatus.getContentText(),
				new Date(), postStatus.getLocation().getxLoc(), postStatus.getLocation().getyLoc(), 
				postStatus.getPrivacy(), postStatus.getIsUseAccount(), hasImage);
		boolean result = statusService.postStatus(status);
		
		System.out.println("Result post: " + result);
		if(result == true){
			return "Post Successfull!!";
		}
		if(hasImage) {
			//TODO: insert image to DB in here
			
		}
		return "post fail";
	}

	@RequestMapping(value = "/mobile/interact/status", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public void likeStatus(HttpServletResponse response) {
		// Can chu y xu ly chi dc like/dislike - 1 lan, xu ly tren ca server
	}
}
