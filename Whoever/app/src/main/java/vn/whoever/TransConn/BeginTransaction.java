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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import vn.whoever.models.supports.Position;
import vn.whoever.TransConn.utils.GPSLocation;

/**
 * Created by spider man on 1/7/2016.
 */
public class BeginTransaction {

    private static BeginTransaction transaction = new BeginTransaction();
    private static Activity myActivity;

    private BeginTransaction() {}
    // LocalAccount user;
    private Integer httpStatusCode = null;

    public static BeginTransaction getTransaction(Activity acctivity) {
        myActivity = acctivity;
        return transaction;
    }

    /**
     * @param langCode, birthday
     *
     * TODO: using GET method
     */

    public void getRequestLoginAnonymous(String langCode) {
        httpStatusCode = null;
        UrlQuery query = new UrlQuery(AddressConnection.url_login_anonymous);
        query.putVariable(langCode);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, query.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ssoId", response);
                /**
                 * TODO: need stored ssoId into database
                 *
                 */
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exTractError(error);
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(myActivity).addToRequestQueue(stringRequest);
    }

    public void registerUser(String ssoId, String password, String nickName, String birthday, String langCode) {

        Map<String, Object> jsonRegister = new LinkedHashMap<>();
        jsonRegister.put("ssoId", ssoId);
        jsonRegister.put("password", password);
        jsonRegister.put("nickName", nickName);
        jsonRegister.put("birthday", birthday);
        jsonRegister.put("langCode", langCode);

        JSONObject profile = new JSONObject(jsonRegister);

        Map<String, Double> loc = new LinkedHashMap<>();

        try {
            Position position = (new GPSLocation(myActivity)).getLocation();

            if(position != null) {
                position.setX(Math.round(position.getX()*1000000)/1000000.0);
                position.setY(Math.round(position.getY()*1000000)/1000000.0);
                loc.put("xLoc", position.getX());
                loc.put("yLoc", position.getY());
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        }

        JSONObject jsonLoc = new JSONObject(loc);

        try {
            profile.put("location",jsonLoc);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("json request", profile.toString());

        JsonObjectRequest registerRequest = new JsonObjectRequest(Request.Method.POST, AddressConnection.url_register,
                profile,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /**
                         * Response status on home page
                         */
                        Log.d("Response from server", response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exTractError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }

        };
        TransactionQueue.getsInstance(myActivity).addToRequestQueue(registerRequest);
    }

    /**
     * For register new user
     * @param ssoId
     */

    public String resultQuerySsoId;

    public String findSsoIdAvaiable(String ssoId) {
        resultQuerySsoId = null;
        UrlQuery urlQuery = new UrlQuery(AddressConnection.url_query_ssoId);
        urlQuery.putParam("ssoId", ssoId);

        Log.d("urlQuery", urlQuery.getUrl());

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
        String urlQuery = AddressConnection.URL_USER + "/term";

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
