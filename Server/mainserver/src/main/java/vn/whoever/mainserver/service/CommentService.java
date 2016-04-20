package vn.whoever.mainserver.service;

import vn.whoever.support.model.request.PostComment;

public interface CommentService {

	public void postComment(String idStatus, PostComment comment);
}
