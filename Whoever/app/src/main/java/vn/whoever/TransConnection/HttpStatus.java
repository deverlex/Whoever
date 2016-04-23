package vn.whoever.TransConnection;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by spider man on 4/13/2016.
 */
public class HttpStatus {

    // TODO: success
    public static final int SC_OK = 200;
    public static final int SC_CREATED = 201;

    // TODO: Server error
    public static final int SC_SERVER_INTERNAL = 500;
    public static final int SC_SERVICE_UNAVAIABLE = 503;

    // TODO: Client error
    public static final int SC_NOT_FOUND = 404;
    public static final int SC_UNAUTHORIZED = 401;

    public static final int SC_BAD_REQUEST = 400;
    public static final int SC_REQUEST_TIMEOUT = 408;

    private static Context context;
    private static HttpStatus httpStatus = new HttpStatus();
    private Toast toast;

    private HttpStatus() {}

    public static HttpStatus getStatus(Context ctx) {
        context = ctx;
        return httpStatus;
    }

    public boolean signalCode(Integer httpCode) {
        if(httpCode == SC_OK || httpCode == SC_CREATED) {
            return true;
        } else if(httpCode == SC_NOT_FOUND) {
            if(toast != null) toast.cancel();
            toast = Toast.makeText(context, "Service isn't finding resource", Toast.LENGTH_SHORT);
            toast.show();
        } else if(httpCode == SC_UNAUTHORIZED) {
            if(toast != null) toast.cancel();
            toast = Toast.makeText(context, "Service isn't authorized your account", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (httpCode == SC_SERVER_INTERNAL || httpCode == SC_SERVICE_UNAVAIABLE) {
            if(toast != null) toast.cancel();
            toast = Toast.makeText(context, "Server is temporarily overloaded", Toast.LENGTH_SHORT);
            toast.show();
        } else if(httpCode == SC_REQUEST_TIMEOUT || httpCode == SC_BAD_REQUEST){
            if(toast != null) toast.cancel();
            toast = Toast.makeText(context, "Check your connection", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if(toast != null) toast.cancel();
            toast = Toast.makeText(context, "Have a error in system", Toast.LENGTH_SHORT);
            toast.show();
        }
        return false;
    }
}
