package vn.whoever.mainserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mobile/status")
public class MobileCommentController {

//	public String getListComment() {
//		
//	}
	
	@RequestMapping(value = {"/comments"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody void postComment() {
		
	}

}
