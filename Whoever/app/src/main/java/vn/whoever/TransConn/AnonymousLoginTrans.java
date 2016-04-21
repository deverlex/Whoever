package vn.whoever.TransConn;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import vn.whoever.models.dao.ConnDB;

/**
 * Created by spider man on 4/21/2016.
 */
public class AnonymousLoginTrans {

    private Activity activity;
    private Integer httpStatusCode = null;
    private String url_anonymous = "http://192.168.1.112:8080/mainserver/mobile/anonymous";

    public AnonymousLoginTrans(Activity activity) {
        this.activity = activity;
    }

    public void getRequestLoginAnonymous(String langCode) {
        httpStatusCode = null;
        UrlQuery query = new UrlQuery(url_anonymous);
        query.putPathVariable(langCode);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, query.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String res) {
                ConnDB.getConn().openDataBase();
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("id", 1);
                values.put("langName", res);
                Log.d("language", res);
                db.execSQL("delete from LocalProfile");
                db.insert("LocalProfile", null, values);
                ConnDB.getConn().close();
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
                Map<String, String> headers = response.headers;
                ConnDB.getConn().openDataBase();
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("token", headers.get("Whoever-token"));
                values.put("expTime", headers.get("Token-expiration"));
                Log.d("token", headers.get("Whoever-token"));
                db.execSQL("delete from Auth");
                db.insert("Auth", null, values);
                ConnDB.getConn().close();
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(stringRequest);
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
