package vn.whoever.mainserver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import v.whoever.service.impl.GenerateIdImpl;
import vn.whoever.mainserver.dao.CommentUserDao;
import vn.whoever.mainserver.dao.CommentsDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.Comments;
import vn.whoever.mainserver.service.CommentService;
import vn.whoever.support.model.request.PostComment;
import vn.whoever.support.model.request.UserInteract;
import vn.whoever.support.model.utils.Interacts;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentsDao commentsDao;
	
	@Autowired
	private CommentUserDao commentUserDao;
	
	public void postNewComment(String idStatus, String idUser,PostComment postComment) {
		String idComment = GenerateIdImpl.generateId().getId();
		System.out.println("Chen vào DB comments@@@@");
		Comments comments = new Comments(idComment, idUser, idStatus, 
				postComment.getContent(), postComment.getIsUseAccount());
		commentsDao.createComment(comments);
	}

	public void commentInteract(String idComment, String idUser,UserInteract interactCmt) {
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
