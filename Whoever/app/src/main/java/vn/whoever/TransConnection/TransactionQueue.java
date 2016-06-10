package vn.whoever.TransConnection;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

/**
 * Created by Nguyen Van Do on 1/7/2016.
 * Making a queue for request to server.
 * This class is singleton
 */
public class TransactionQueue {

    public static final String TAG = "Whoever_Queue_Request";

    private static Context applicationContext;

    private RequestQueue mRequestQueue;

    private static TransactionQueue controller = new TransactionQueue();

    public static synchronized TransactionQueue getsInstance(Context context) {
        applicationContext = context;
        return controller;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(applicationContext);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        // Timeout of a request is 10 second
        int socketTimeout = 10000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        // Add a request into queue request
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Objects tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
