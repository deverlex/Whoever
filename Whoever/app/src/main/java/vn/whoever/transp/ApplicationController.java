package vn.whoever.transp;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

/**
 * Created by spider man on 1/7/2016.
 */
public class ApplicationController {

    public static final String TAG = "Whoever_Queue_Request";

    private static Context applicationContext;

    private RequestQueue mRequestQueue;

    private static ApplicationController controller = new ApplicationController();

    public static synchronized ApplicationController getsInstance(Context context) {
        applicationContext = context;
        return controller;
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(applicationContext);
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", request.getUrl());

        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);

        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Objects tag) {
        if(mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
