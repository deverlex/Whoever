package vn.whoever.transactionconn;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by spider man on 4/13/2016.
 */
public class HttpStatus {

    public static final int SC_BAD_REQUEST = 400;
    public static final int SC_REQUEST_TIMEOUT = 408;
    public static final int SC_SERVICE_UNAVAIABLE = 503;
    public static final int SC_NOT_FOUND = 404;
    public static final int SC_OK = 200;
    public static final int SC_CREATED = 201;
    public static final int SC_UNAUTHORIZED = 401;

    private static Context context;
    private static HttpStatus httpStatus = new HttpStatus();
    private Toast toast;

    private HttpStatus() {}

    public static HttpStatus getStatus(Context ctx) {
        context = ctx;
        return httpStatus;
    }

    public boolean codeSignInAnonymous(Integer httpCode) {
        if(httpCode == SC_BAD_REQUEST || httpCode == SC_REQUEST_TIMEOUT) {
            if(toast != null) toast.cancel();
            toast = Toast.makeText(context, "Check your connection, please!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if(httpCode == SC_CREATED || httpCode == SC_OK) {
                return true;
            } else if(httpCode == SC_NOT_FOUND) {
                if(toast != null) toast.cancel();
                toast = Toast.makeText(context, "We don't support your language", Toast.LENGTH_SHORT);
                toast.show();
            } else if(httpCode == SC_SERVICE_UNAVAIABLE) {
                if(toast != null) toast.cancel();
                toast = Toast.makeText(context, "Server is temporarily overloaded", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        return false;
    }

    public boolean codeSignIn(Integer httpCode) {
        if(httpCode == SC_BAD_REQUEST || httpCode == SC_REQUEST_TIMEOUT) {
            if(toast != null) toast.cancel();
            toast = Toast.makeText(context, "Check your connection, please!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if(httpCode == SC_OK) {
                return true;
            } else if(httpCode == SC_SERVICE_UNAVAIABLE) {
                if(toast != null) toast.cancel();
                toast = Toast.makeText(context, "Server is temporarily overloaded", Toast.LENGTH_SHORT);
                toast.show();
            } else if(httpCode == SC_UNAUTHORIZED) {
                if(toast != null) toast.cancel();
                toast = Toast.makeText(context, "Please check your password or ID account!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        return false;
    }
}
