package vn.whoever.utils;

/**
 * Created by spider man on 12/26/2015.
 * TODO: check form email, password, nickname...
 */
public class RegexUtils {

    public static final String emailPattern = "^([a-zA-Z0-9])+([a-zA-Z0-9_])+@([a-zA-Z])+(\\.[a-zA-Z]+)*(\\.[a-zA-Z]+)$";
    public static final String nickNamePattern = "^([a-zA-Z0-9]+)+(\\s[a-zA-Z0-9]+)*(\\s[a-zA-Z0-9]+)*$";
    public static final String passWordPattern = "([^\\s]{8,32})$";

    public static boolean checkEmail(String email) {
        /**
         * TODO: process split string before pattern
         */
        email = standardizeString(email);
        return email.matches(emailPattern);
    }

    public static boolean checkPassword(String password) {
        password = standardizeString(password);
        return password.matches(passWordPattern);
    }

    public static boolean checkNickName(String nickName) {
        nickName = standardizeString(nickName);
        return nickName.matches(nickNamePattern);
    }

    public static String standardizeString(String str) {
        str = str.replaceAll("\\s+", " ");
        return str.trim();
    }

    public static String getTagInMessage(String msg) {
        /**
         *  TODO: check tag name => return a name tag
         */

        return null;
    }
}
