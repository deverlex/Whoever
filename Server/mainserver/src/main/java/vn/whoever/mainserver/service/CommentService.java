package vn.whoever.mainserver.service;

import java.util.List;

import vn.whoever.mainserver.model.Comments;
import vn.whoever.support.model.request.InteractComment;
import vn.whoever.support.model.request.PostComment;
import vn.whoever.support.model.utils.Interacts;

public interface CommentService {

	public void postComment(String idStatus, PostComment comment);
	
	public void interactComment(String idComment, InteractComment interactCmt);
	
	public List<Comments> getListComment(String idStatus);
	
	public Interacts getInteractCommentState(String idComment, String idUser);
}
