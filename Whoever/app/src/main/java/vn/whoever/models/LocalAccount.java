package vn.whoever.models;

import java.io.Serializable;

/**
 * Created by Nguyen Van Do on 12/31/2015.
 * This class for test UI - not is class in this application
 */
public class LocalAccount implements Serializable {

    private static final long serialVersionUID = 1545323565773L;

    private String ssoId;
    private String password;
    private String langCode;

    public LocalAccount() {
    }

    public LocalAccount(String ssoId, String password, String langCode) {
        this.ssoId = ssoId;
        this.password = password;
        this.langCode = langCode;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getSsoId() {
        return ssoId;
    }

    public String getPassword() {
        return password;
    }

    public String getLangCode() {
        return langCode;
    }
}
