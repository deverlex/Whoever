package vn.whoever.TransConn;

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
public class ContactQuery {

    private static ContactQuery transaction = new ContactQuery();
    private static Activity myActivity;

    private ContactQuery() {}
    // LocalAccount user;
    private Integer httpStatusCode = null;

    public static ContactQuery getTransaction(Activity acctivity) {
        myActivity = acctivity;
        return transaction;
    }

    /**
     * @param langCode, birthday
     *
     * TODO: using GET method
     */

    /**
     * For register new user
     * @param ssoId
     */

    public String resultQuerySsoId;

    public String findSsoIdAvaiable(String ssoId) {
        String url_query = "http://192.168.1.112:8080/mainserver/mobile/query";

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
        TransactionQueue.getsInstance(myActivity).addToRequestQueue(findSsoId);
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
        TransactionQueue.getsInstance(myActivity).addToRequestQueue(objectRequest);
    }

    public String getQuerySsoId() {
        return resultQuerySsoId;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    private void exTractError(VolleyError error) {
        NetworkResponse networkResponse = error.networkResponse;
        if(networkResponse != null) {
            httpStatusCode = networkResponse.statusCode;
        }

        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            httpStatusCode = HttpStatus.SC_BAD_REQUEST;
        } else if (error instanceof AuthFailureError) {
            //TODO
        } else if (error instanceof ServerError) {
            httpStatusCode = HttpStatus.SC_SERVICE_UNAVAIABLE;
        } else if (error instanceof NetworkError) {
            httpStatusCode = HttpStatus.SC_BAD_REQUEST;
        } else if (error instanceof ParseError) {
            //TODO
        }
    }
}
