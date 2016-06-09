package vn.whoever.mainserver.dao;

import java.util.List;

import vn.whoever.mainserver.model.Comments;

public interface CommentsDao {

	public void createComment(Comments comment);

	public void updateComment(Comments comment);

	public List<Comments> getListComments(String idStatus);

	public void deleteComment(String idComment);

	public int getTotalCommentStatus(String idStatus);
}
