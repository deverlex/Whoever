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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spider man on 4/21/2016.
 */
public class LoginTrans {

    private Activity activity;
    private Integer httpStatusCode = null;
    private String url_login = "http://192.168.1.112:8080/mainserver/mobile/login";

    public LoginTrans(Activity activity) {
        this.activity = activity;
    }

    public void getRequestLogin(final String ssoId, final String password) {
        httpStatusCode = null;

        Map<String, String> jsonLogin = new HashMap<>();
        jsonLogin.put("ssoId", ssoId);
        jsonLogin.put("password", password);

        final JsonObjectRequest requestLogin = new JsonObjectRequest(Request.Method.POST, url_login, new JSONObject(jsonLogin) ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("returnLogin", response.toString());
                try {
                    String lang = response.getString("langName");

                } catch (Exception e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exTractError(error);
            }
        }){

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

        TransactionQueue.getsInstance(activity).addToRequestQueue(requestLogin);
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
