package vn.whoever.mainserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.model.Status;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.request.PostStatus;

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
	
	@RequestMapping(value = "/mobile/gethome", method = RequestMethod.GET)
	public @ResponseBody String getHome(HttpServletResponse httpResponse,
			@RequestParam(value = "langCode", required = true, defaultValue = "en") String langCode) {
		
		return "";
	}
	
	@RequestMapping(value = "/mobile/getnews", method = RequestMethod.GET,
			produces = "application/json")
	public @ResponseBody String getNews(HttpServletResponse httpResponse,
			@RequestParam(value = "langCode", required = true, defaultValue = "en") String langCode) {

		return "new status for you";
	}
	
	@RequestMapping(value = "/mobile/post/status", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody void postStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PostStatus postStatus) {
		
	}
	
	@RequestMapping(value = "/mobile/interact/status", method = RequestMethod.GET)
	public void likeStatus(HttpServletResponse response) {
		//Can chu y xu ly chi dc like/dislike - 1 lan, xu ly tren ca server
	}
}
