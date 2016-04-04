package vn.whoever.models;

import vn.whoever.models.supports.Genders;
import vn.whoever.models.supports.Privacies;

/**
 * Created by spider man on 4/4/2016.
 */
public class LocalProfile {

    private String nickName;
    private String birthday;
    private Genders gender = Genders.unknown;
    private String mobile;
    private String email;
    private Privacies privacy = Privacies.normal;

    public LocalProfile() {}

    public LocalProfile(String nickName, String birthday, Genders gender, String mobile, String email, Privacies privacy) {
        this.nickName = nickName;
        this.birthday = birthday;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.privacy = privacy;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Privacies getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacies privacy) {
        this.privacy = privacy;
    }
}
