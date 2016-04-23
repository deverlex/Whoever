package vn.whoever.TransConnection;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.whoever.models.dao.ConnDB;

/**
 * Created by spider man on 4/22/2016.
 */
public class CommentTrans {

    private Activity activity;
    private Integer httpStatusCode = null;
    
    public CommentTrans(Activity activity) {
        this.activity = activity;
    }

    public void getCommentOfStatus(final String idStatus) {
        String url_get_comment = "http://192.168.1.112:8080/mainserver/mobile/status";
        UrlQuery urlQuery = new UrlQuery(url_get_comment);
        urlQuery.putPathVariable(idStatus);
        urlQuery.putPathVariable("comments");

        Log.d("urlGetComment", urlQuery.getUrl());

        JsonArrayRequest requestComment = new JsonArrayRequest(Request.Method.GET, urlQuery.getUrl(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                ContentValues values = new ContentValues();
                for(int i = 0; i < resp.length(); ++i) {
                    values.put("idStatus", idStatus);
                    try {
                        JSONObject obj = resp.getJSONObject(i);
                        values.put("idComment", obj.getString("idComment"));
                        values.put("ssoIdPoster", obj.getString("ssoIdPoster"));
                        values.put("namePoster", obj.getString("namePoster"));
                        values.put("avatarPoster", obj.getString("avatarPoster"));
                        values.put("content", obj.getString("content"));
                        values.put("timePost", obj.getString("timePost"));
                        values.put("totalLike", obj.getInt("totalLike"));
                        values.put("totalDislike", obj.getInt("totalDislike"));
                        values.put("interact", obj.getString("interact"));
                        db.insert("Comment", null, values);
                    } catch (JSONException e) {
                        Log.d("insertComment", "error insert!!!");
                    }
                    values.clear();
                }
                db.close();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exTractError(error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                return onCreateHeaders();
            }

            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(requestComment, "requestComment");
    }

    public void interactComment(String type, String idStatus, String idComment) {
        String url_interact = "http://192.168.1.112:8080/mainserver/mobile/status";
        UrlQuery urlQuery = new UrlQuery(url_interact);
        urlQuery.putPathVariable(idStatus);
        StringRequest requestInteract = new StringRequest(Request.Method.GET, urlQuery.getUrl(),
                new Response.Listener<String>() {
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
                return onCreateHeaders();
            }

            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(requestInteract, "requestInteractStatus");
    }

    public Map<String, String> onCreateHeaders () {
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
