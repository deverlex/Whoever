package vn.whoever.mainserver.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * return 10 item status for a request get status
 * 
 * @author spider man
 *
 */

@Controller
public class MobileStatusController {
	
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
	public void postStatus(HttpServletResponse response) {
		
	}
	
	@RequestMapping(value = "/mobile/interact/status", method = RequestMethod.GET)
	public void likeStatus(HttpServletResponse response) {
		//Can chu y xu ly chi dc like/dislike - 1 lan, xu ly tren ca server
	}
}