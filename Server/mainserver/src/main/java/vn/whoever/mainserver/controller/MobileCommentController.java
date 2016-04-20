package vn.whoever.mainserver.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.service.CommentService;
import vn.whoever.support.model.request.InteractComment;
import vn.whoever.support.model.request.PostComment;

@Controller
@RequestMapping("/mobile/status")
public class MobileCommentController {

	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = {"/{idStatus}/comments"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody void postComment(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable(value = "idStatus") String idStatus,
			@RequestBody PostComment postComment) throws IOException {
		
		try {
			commentService.postComment(idStatus, postComment);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		
	}
	
	@RequestMapping(value = {"/{idStatus}/comments/{idComment}"}, method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody void interactComment(@PathVariable(value = "idStatus") String idStatus, 
			@PathVariable(value = "idComment") String idComment, @RequestBody InteractComment interactComment) {
		
		
	}
	
	

}
