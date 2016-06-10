package vn.whoever.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nguyen Van Do on 1/10/2016.
 * This class isn't complete at now
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 154595884L;

    private long id;
    private boolean isMe;
    private String message;
    private Long userId;
    private Date dateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getIsme() {
        return isMe;
    }

    public void setMe(boolean isMe) {
        this.isMe = isMe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return dateTime;
    }

    public void setDate(Date dateTime) {
        this.dateTime = dateTime;
    }
}
