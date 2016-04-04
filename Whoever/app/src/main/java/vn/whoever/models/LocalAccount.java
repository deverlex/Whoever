package vn.whoever.models;

/**
 * Created by spider man on 12/31/2015.
 */
public class LocalAccount {

    private String ssoId;
    private String password;
    private Double xLoc;
    private Double yLoc;
    private String langCode;

    public LocalAccount() {}

    public LocalAccount(String ssoId, String password, Double xLoc, Double yLoc, String langCode) {
        this.ssoId = ssoId;
        this.password = password;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.langCode = langCode;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setxLoc(Double xLoc) {
        this.xLoc = xLoc;
    }

    public void setyLoc(Double yLoc) {
        this.yLoc = yLoc;
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

    public Double getxLoc() {
        return xLoc;
    }

    public Double getyLoc() {
        return yLoc;
    }

    public String getLangCode() {
        return langCode;
    }
}
