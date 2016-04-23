package vn.whoever.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by spider man on 1/10/2016.
 */
public class Status implements Serializable {

    private static final long serialVersionUID = 154532345653L;

    private int id;
    private String idStatus;
    private String ssoIdPoster;
    private String avatarPoster;
    private String namePoster;
    private String timePost;
    private String contentText;
    private String contentImage;
    private int totalLike;
    private int totalDislike;
    private int totalComment;
    private String interact;

    public Status() {}

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

    public String getSsoIdPoster() {
        return ssoIdPoster;
    }

    public void setSsoIdPoster(String ssoIdPoster) {
        this.ssoIdPoster = ssoIdPoster;
    }

    public String getAvatarPoster() {
        return avatarPoster;
    }

    public void setAvatarPoster(String avatarPoster) {
        this.avatarPoster = avatarPoster;
    }

    public String getNamePoster() {
        return namePoster;
    }

    public void setNamePoster(String namePoster) {
        this.namePoster = namePoster;
    }

    public String getTimePost() {
        return timePost;
    }

    public void setTimePost(String timePost) {
        this.timePost = timePost;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentImage() {
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public int getTotalDislike() {
        return totalDislike;
    }

    public void setTotalDislike(int totalDislike) {
        this.totalDislike = totalDislike;
    }

    public int getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
    }

    public String getInteract() {
        return interact;
    }

    public void setInteract(String interact) {
        this.interact = interact;
    }
}
