package vn.whoever.mainserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MobileCommentController {

	@RequestMapping(value = {"/mobile/post/comment"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody void postComment() {
		
	}
	
	public void getListComment() {
		
	}
}
