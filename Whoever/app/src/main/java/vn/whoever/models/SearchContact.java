package vn.whoever.models;

import java.io.Serializable;

/**
 * Created by spider man on 1/10/2016.
 */
public class SearchContact implements Serializable {

    private static final long serialVersionUID = 154532345653L;

    private String ssoId;
    private String nickName;
    private String avatar;
    private boolean isFriend;

    public SearchContact() {}

    public boolean isFriend() {
        return isFriend;
    }

    public void setIsFriend(boolean isFriend) {
        this.isFriend = isFriend;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
