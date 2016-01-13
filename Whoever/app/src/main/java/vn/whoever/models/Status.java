package vn.whoever.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by spider man on 1/10/2016.
 */
public class Status {

    private int idStatus;
    private String contentStatus;
    private User senderStatus;
    private Date dateWriterStatus;
    private String imageUpload;
    private int countComment;
    private int countLike;
    private int countDislike;

    private HashMap<String, Reply> comments; //idUser & Reply
    private ArrayList<String> likes; //idUser
    private ArrayList<String> dislikes; //idUser

    public Status() {
        comments = new HashMap<>();
        likes = new ArrayList<>();
        dislikes = new ArrayList<>();
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    public User getSenderStatus() {
        return senderStatus;
    }

    public void setSenderStatus(User senderStatus) {
        this.senderStatus = senderStatus;
    }

    public Date getDateWriterStatus() {
        return dateWriterStatus;
    }

    public void setDateWriterStatus(Date dateWriterStatus) {
        this.dateWriterStatus = dateWriterStatus;
    }

    public String getImageUpload() {
        return imageUpload;
    }

    public void setImageUpload(String imageUpload) {
        this.imageUpload = imageUpload;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public int getCountComment() {
        return countComment;
    }

    public void setCountComment(int countComment) {
        this.countComment = countComment;
    }

    public int getCountDislike() {
        return countDislike;
    }

    public void setCountDislike(int countDislike) {
        this.countDislike = countDislike;
    }

    public HashMap<String, Reply> getComments() {
        return comments;
    }

    public void setComments(HashMap<String, Reply> comments) {
        this.comments = comments;
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }

    public ArrayList<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(ArrayList<String> dislikes) {
        this.dislikes = dislikes;
    }
}
