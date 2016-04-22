package vn.whoever.TransConn;

import android.app.Activity;
import android.content.SyncAdapterType;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import vn.whoever.models.dao.ConnDB;

/**
 * Created by spider man on 4/22/2016.
 */
public class CommentTrans {

    private Activity activity;
    private Integer httpStatusCode = null;
    private String url_get_comment = "http://localhost:8080/mainserver/mobile/status";
    
    public CommentTrans(Activity activity) {
        this.activity = activity;
    }

    public void getCommentOfStatus(String idStatus) {

        UrlQuery urlQuery = new UrlQuery(url_get_comment);
        urlQuery.putPathVariable(idStatus);
        urlQuery.putPathVariable("comments");

        Log.d("urlGetComment", urlQuery.getUrl());
        JsonArrayRequest requestComment = new JsonArrayRequest(Request.Method.GET, urlQuery.getUrl(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
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

            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(requestComment, "requestComment");
    }
}
