package vn.whoever.models;

import java.util.Date;

/**
 * Created by spider man on 1/10/2016.
 */
public class Reply {

    private int idReply;
    private String content;
    private Date timeUpload;
    private int countLike;
    private int countDislike;
    private LocalAccount userReply;

    public int getIdReply() {
        return idReply;
    }

    public void setIdReply(int idReply) {
        this.idReply = idReply;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeUpload() {
        return timeUpload;
    }

    public void setTimeUpload(Date timeUpload) {
        this.timeUpload = timeUpload;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public int getCountDislike() {
        return countDislike;
    }

    public void setCountDislike(int countDislike) {
        this.countDislike = countDislike;
    }

    public LocalAccount getUserReply() {
        return userReply;
    }

    public void setUserReply(LocalAccount userReply) {
        this.userReply = userReply;
    }
}
