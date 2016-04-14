package vn.whoever.transactionconn;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Created by spider man on 1/7/2016.
 */
public class StatusTransaction {

    private static Activity myActivity;
    private static StatusTransaction statusTransaction = new StatusTransaction();

    private Integer httpStatusCode = null;

    private StatusTransaction() {}

    public static StatusTransaction getTransaction(Activity activity) {
        myActivity = activity;
        return statusTransaction;
    }

    public void getNewsFeed(String ssoId, String order, int offset) {

        //JsonObjectRequest newsRequest = new JsonObjectRequest(Request.Method.POST, )
    }

}
