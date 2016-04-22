package vn.whoever.TransConn;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import vn.whoever.models.dao.ConnDB;

/**
 * Created by spider man on 4/22/2016.
 */
public class InteractTrans {

    private Activity activity;
    private Integer httpStatusCode = null;
    private String url_interact = "http://192.168.1.112:8080/mainserver/mobile/status";

    public InteractTrans(Activity activity) {
        this.activity = activity;
    }

    public void interact(String type, String idStatus, String idComment) {
        UrlQuery urlQuery = new UrlQuery(url_interact);
        urlQuery.putPathVariable(idStatus);
        if(type.equals("comments")) {
            urlQuery.putPathVariable("comments");
            urlQuery.putPathVariable(idComment);
        }
        StringRequest requestInteract = new StringRequest(Request.Method.GET, urlQuery.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //nothing
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exTractError(error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                Cursor cursor = db.rawQuery("Select token, expTime from Auth", null);
                String token = "";
                String expTime = "";
                while (cursor.moveToNext()) {
                    token = cursor.getString(0);
                    expTime = cursor.getString(1);
                }
                cursor.close();
                db.close();
                headers.put("Whoever-token", token);
                headers.put("Token-expiration", expTime);
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }

            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(requestInteract, "requestInteract");
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
