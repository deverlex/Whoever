package vn.whoever.mainserver.service;

import vn.whoever.support.model.request.InteractComment;
import vn.whoever.support.model.request.PostComment;

public interface CommentService {

	public void postComment(String idStatus, PostComment comment);
	
	public void interactComment(String idComment, InteractComment interactCmt);
}
