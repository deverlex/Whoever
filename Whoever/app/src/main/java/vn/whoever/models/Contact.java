package vn.whoever.models;

/**
 * Created by spider man on 5/1/2016.
 */
public class Contact {

    private String groupName;
    private String ssoId;
    private String nickName;
    private String latestStatus;
    private String latestOnline;

    public Contact() {}

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
