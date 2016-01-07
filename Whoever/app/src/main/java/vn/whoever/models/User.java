package vn.whoever.models;

/**
 * Created by spider man on 12/31/2015.
 */
public class User {

    private int id;
    private String nickName;
    private String email;
    private String password;

    public User() {}

    public User(int id, String nickName, String email, String password) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
