package vn.whoever.mainserver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.mainserver.dao.CommentUserDao;
import vn.whoever.mainserver.dao.CommentsDao;
import vn.whoever.mainserver.model.Comments;
import vn.whoever.mainserver.service.CommentService;
import vn.whoever.service.impl.GenerateIdImpl;
import vn.whoever.support.model.request.PostComment;
import vn.whoever.support.model.request.UserInteract;
import vn.whoever.support.model.utils.Interacts;

/**
 * @author Nguyen Van Do
 *	This service provide functions for comment action.
 *	Example: post new comment, interaction, get list comment.
 */

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentsDao commentsDao;

	@Autowired
	private CommentUserDao commentUserDao;

	public void postNewComment(String idStatus, String idUser, PostComment postComment) {
		// Generation id for comment.
		String idComment = GenerateIdImpl.generateId().getId();
		Comments comments = new Comments(idComment, idUser, idStatus, postComment.getContent(),
				postComment.getIsUseAccount());
		// Save comment to database
		commentsDao.createComment(comments);
	}
	
	// Save interaction of user on comment: like/dislike
	public void commentInteract(String idComment, String idUser, UserInteract interactCmt) {
		commentUserDao.addInteractComment(idComment, idUser, interactCmt.getInteract());
	}
	
	public List<Comments> getListComment(String idStatus) {
		return commentsDao.getListComments(idStatus);
	}

	public Interacts getInteractCommentState(String idComment, String idUser) {
		return commentUserDao.getInteractStateComment(idComment, idUser);
	}

	public int getTotalLikeComment(String idComment) {
		return commentUserDao.getTotalInteract(idComment, Interacts.like);
	}

	public int getTotalDislikeComment(String idComment) {
		return commentUserDao.getTotalInteract(idComment, Interacts.dislike);
	}
}
