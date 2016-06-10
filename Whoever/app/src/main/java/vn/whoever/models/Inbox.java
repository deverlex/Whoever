package vn.whoever.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nguyen Van Do on 1/13/2016.
 * This class isnn't complete at now
 */
public class Inbox implements Serializable {

    private static final long serialVersionUID = 154532354503943L;

    private long idMessage;
    private LocalAccount userSend;
    private Group groupSend;
    private boolean isRead;
    private Date lastTimeChat;
    private String lastMessage;

    public LocalAccount getUserSend() {
        return userSend;
    }

    public void setUserSend(LocalAccount userSend) {
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
