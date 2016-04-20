package vn.whoever.mainserver.service;

import java.util.List;

import vn.whoever.mainserver.model.Comments;
import vn.whoever.support.model.request.PostComment;
import vn.whoever.support.model.request.UserInteract;
import vn.whoever.support.model.utils.Interacts;

public interface CommentService {

	public void postNewComment(String idStatus, String idUser, PostComment comment);
	
	public void commentInteract(String idComment, String idUser, UserInteract interactCmt);
	
	public List<Comments> getListComment(String idStatus);
	
	public Interacts getInteractCommentState(String idComment, String idUser);
}
