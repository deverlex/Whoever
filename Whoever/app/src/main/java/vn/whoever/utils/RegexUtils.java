package vn.whoever.utils;

/**
 * Created by Nguyen Van Do on 12/26/2015.
 * TODO: check form email, password, nickname...
 */
public class RegexUtils {

    public static final String emailPattern = "^([a-zA-Z0-9])+([a-zA-Z0-9_])+@([a-zA-Z])+(\\.[a-zA-Z]+)*(\\.[a-zA-Z]+)$";
    public static final String nickNamePattern = "^(\\p{L}+)*(\\s\\p{L}*)*$";
    public static final String passWordPattern = "([^\\s]{8,32})$";
    public static final String ssoIdPattern = "^([a-zA-Z0-9])+([a-zA-Z0-9_])*([a-zA-Z0-9])+$";

    private static RegexUtils regexUtils = new RegexUtils();

    public static RegexUtils getInstance() {
        return regexUtils;
    }

    //Check the validity of email
    public boolean checkEmail(String email) {
        /**
         * TODO: process split string before pattern
         */
        email = standardizeString(email);
        return email.matches(emailPattern) && email.length() > 8;
    }

    // Check the validity of SSOID
    public boolean checkSsoId(String ssoId) {
        ssoId = standardizeString(ssoId);
        return ssoId.matches(ssoIdPattern) && ssoId.length() > 7;
    }

    // Check the validity of password
    public boolean checkPassword(String password) {
        password = standardizeString(password);
        return password.matches(passWordPattern);
    }

    //Check the validity of nickname/user account
    public boolean checkNickName(String nickName) {
        nickName = standardizeString(nickName);
        return nickName.matches(nickNamePattern) && nickName.length() > 3;
    }

    // TODO: standardized string
    public String standardizeString(String str) {
        str = str.replaceAll("\\s+", " ");
        return str.trim();
    }

    public String getTagInMessage(String msg) {
        /**
         *  TODO: check tag name => return a name tag
         */
        return null;
    }
}
