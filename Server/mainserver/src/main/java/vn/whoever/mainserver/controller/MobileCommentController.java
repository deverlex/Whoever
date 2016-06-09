package vn.whoever.mainserver.controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.model.Comments;
import vn.whoever.mainserver.model.Status;
import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.mainserver.service.CommentService;
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.mainserver.service.StatusService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.request.PostComment;
import vn.whoever.support.model.request.UserInteract;
import vn.whoever.support.response.ReturnComment;
import vn.whoever.support.utils.TimePost;

/**
 * @author Nguyen Van Do
 * 
 * This file provide comment status function: get list comment, post comment
 * and like/dislike comment 
 */

@Controller
@RequestMapping("/mobile/status")
public class MobileCommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private UsersService userService;

	@Autowired
	private ProfilesService profileService;

	@Autowired
	private AuthToken authToken;

	@RequestMapping(value = {
			"/{idStatus}/comments" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody void postComment(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = "idStatus") String idStatus, @RequestBody PostComment postComment)
			throws IOException {

		/*
		 * JSON string describe { "content" : "", "isUseAccount" : "" }
		 */
		try {
			Status status = statusService.getStatus(idStatus);
			// When users comments on status that is a interaction,
			// so status need set new time for sorting status on newsfeed by
			// time interaction
			status.setTimeUp(new Date());
			statusService.updateStatus(status);
			commentService.postNewComment(idStatus, authToken.getIdUserHttp(request), postComment);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}

	}

	@RequestMapping(value = {
			"/{idStatus}/comments/{idComment}" }, method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public @ResponseBody void interactComment(HttpServletRequest request,
			@PathVariable(value = "idStatus") String idStatus, @PathVariable(value = "idComment") String idComment,
			@RequestBody UserInteract interactCmt) {

		// update again timeUp status when like/dislike from users's action
		try {
			Status status = statusService.getStatus(idStatus);
			status.setTimeUp(new Date());
			statusService.updateStatus(status);

			// insert to Database
			commentService.commentInteract(idComment, authToken.getIdUserHttp(request), interactCmt);
		} catch (Exception e) {
		}
	}

	// @RequestMapping(value = {"/{idStatus}/comments/{idComment}"}, method =
	// RequestMethod.PUT,
	// consumes = "application/json", produces = "application/json")
	// public @ResponseBody void modifyComment() {
	//
	// }

	/**
	 * 
	 * This method handle request get list comment from users And response list
	 * comment of a status to users
	 * 
	 * Information is described below { "idComment" : "", "ssoIdPoster" : "",
	 * "namePoster" : "", "avatarPoster" : "", "content" : "", "timePost" : "",
	 * "totalLike" : "", "totalDislike" : "" }
	 * 
	 */
	@RequestMapping(value = { "/{idStatus}/comments" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ReturnComment> getListCommentStatus(HttpServletRequest request,
			@PathVariable(value = "idStatus") String idStatus) {

		// get list comment by idStatuus
		List<Comments> listCmt = commentService.getListComment(idStatus);
		List<ReturnComment> lReturnCmt = new LinkedList<ReturnComment>();

		// set response to Java Object before convert to JSON
		// if user use anonymous mode -> hiding some information of poster
		for (Comments comment : listCmt) {
			ReturnComment returnCmt = new ReturnComment();
			returnCmt.setIdComment(comment.getIdComment());
			returnCmt.setContent(comment.getContent());
			returnCmt.setTimePost(TimePost.getTimePost(comment.getTimePost()));
			if (comment.getIsUseAccount()) {

				String ssoId = userService.findSsoIdbyIdUser(comment.getIdUser());
				String nickName = profileService.getNickName(comment.getIdUser());

				returnCmt.setSsoIdPoster(ssoId);
				if (nickName == null)
					nickName = "anonymous";

				returnCmt.setNamePoster(nickName);
				returnCmt.setAvatarPoster("haven't a avatar");
			} else {
				returnCmt.setAvatarPoster(null);
				returnCmt.setNamePoster("anonymous");
				returnCmt.setSsoIdPoster(null);
			}
			returnCmt.setTotalLike(commentService.getTotalLikeComment(comment.getIdComment()));
			returnCmt.setTotalDislike(commentService.getTotalDislikeComment(comment.getIdComment()));
			returnCmt.setTimePost(TimePost.getTimePost(comment.getTimePost()));
			returnCmt.setInteract(
					commentService.getInteractCommentState(comment.getIdComment(), authToken.getIdUserHttp(request)));
			lReturnCmt.add(returnCmt);
		}
		return lReturnCmt;
	}
}
