package vn.whoever.TransConnection;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

/**
 * Created by spider man on 1/7/2016.
 */
public class ContactQuery extends AbstractTransaction {

    public ContactQuery(Activity activity) {
        super(activity);
    }

    public String resultQuerySsoId;

    public String findSsoIdAvaiable(String ssoId) {
        String url_query = address + "/users/query";

        resultQuerySsoId = null;
        UrlQuery urlQuery = new UrlQuery(url_query);
        urlQuery.putRequestParam("ssoId", ssoId);

        StringRequest findSsoId = new StringRequest(Request.Method.GET, urlQuery.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                resultQuerySsoId = response;
                Log.d("querySsoId", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exTractError(error);
            }
        });
        TransactionQueue.getsInstance(activity).addToRequestQueue(findSsoId);
        return resultQuerySsoId;
    }

    public void getTermUser() {
        String urlQuery = "http://192.168.1.112:8080/mainserver/mobile/term";

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, urlQuery, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        TransactionQueue.getsInstance(activity).addToRequestQueue(objectRequest);
    }

    public String getQuerySsoId() {
        return resultQuerySsoId;
    }
}
