package vn.whoever.models;

import java.util.Date;

/**
 * Created by spider man on 1/13/2016.
 */
public class Inbox {

    private long idMessage;
    private User userSend;
    private Group groupSend;
    private boolean isRead;
    private Date lastTimeChat;
    private String lastMessage;

    public User getUserSend() {
        return userSend;
    }

    public void setUserSend(User userSend) {
        this.userSend = userSend;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Group getGroupSend() {
        return groupSend;
    }

    public void setGroupSend(Group groupSend) {
        this.groupSend = groupSend;
    }

    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

    public Date getLastTimeChat() {
        return lastTimeChat;
    }

    public void setLastTimeChat(Date lastTimeChat) {
        this.lastTimeChat = lastTimeChat;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
