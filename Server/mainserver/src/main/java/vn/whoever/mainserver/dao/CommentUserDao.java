package vn.whoever.mainserver.dao;

import vn.whoever.support.model.utils.Interacts;

public interface CommentUserDao {

	public void addInteractComment(String idComment, String idUser, Interacts interact);

	public Interacts getInteractStateComment(String idComment, String idUser);

	public int getTotalInteract(String idComment, Interacts interact);
}
