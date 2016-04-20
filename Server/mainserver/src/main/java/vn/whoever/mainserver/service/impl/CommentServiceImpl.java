package vn.whoever.mainserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import v.whoever.service.impl.GenerateIdImpl;
import vn.whoever.mainserver.dao.CommentsDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.Comments;
import vn.whoever.mainserver.service.CommentService;
import vn.whoever.support.model.request.InteractComment;
import vn.whoever.support.model.request.PostComment;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentsDao commentsDao;
	
	@Autowired
	private UsersDao usersDao;
	
	public void postComment(String idStatus, PostComment postComment) {
		String idUser = usersDao.findIdUser(postComment.getSsoId());
		String idComment = GenerateIdImpl.generateId().getId();
		Comments comments = new Comments(idComment, idUser, idStatus, 
				postComment.getContent(), postComment.getIsUseAccount());
		commentsDao.createComment(comments);
	}

	public void interactComment(String idComment, InteractComment interactCmt) {
		
	}

}
