package vn.whoever.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by spider man on 1/10/2016.
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = 156542764322L;

    private int id;
    private String idStatus;
    private String idComment;
    private String ssoIdPoster;
    private String namePoster;
    private String avatarPoster;
    private String content;
    private String timePost;
    private int totalLike;
    private int totalDislike;
    private String interact;

    public Comment() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public String getSsoIdPoster() {
        return ssoIdPoster;
    }

    public void setSsoIdPoster(String ssoIdPoster) {
        this.ssoIdPoster = ssoIdPoster;
    }

    public String getNamePoster() {
        return namePoster;
    }

    public void setNamePoster(String namePoster) {
        this.namePoster = namePoster;
    }

    public String getAvatarPoster() {
        return avatarPoster;
    }

    public void setAvatarPoster(String avatarPoster) {
        this.avatarPoster = avatarPoster;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimePost() {
        return timePost;
    }

    public void setTimePost(String timePost) {
        this.timePost = timePost;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public String getInteract() {
        return interact;
    }

    public void setInteract(String interact) {
        this.interact = interact;
    }

    public int getTotalDislike() {
        return totalDislike;
    }

    public void setTotalDislike(int totalDislike) {
        this.totalDislike = totalDislike;
    }
}
