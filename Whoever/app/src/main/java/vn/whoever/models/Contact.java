package vn.whoever.models;

import java.io.Serializable;

/**
 * Created by Nguyen Van Do on 5/1/2016.
 * Class describe Contact data object
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = 154532343947723L;

    private String groupName;
    private String ssoId;
    private String nickName;
    private String latestStatus;
    private String latestOnline;

    public Contact() {
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLatestStatus() {
        return latestStatus;
    }

    public void setLatestStatus(String latestStatus) {
        this.latestStatus = latestStatus;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLatestOnline() {
        return latestOnline;
    }

    public void setLatestOnline(String latestOnline) {
        this.latestOnline = latestOnline;
    }
}
