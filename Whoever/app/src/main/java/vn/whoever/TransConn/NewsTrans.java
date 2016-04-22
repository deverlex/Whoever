package vn.whoever.TransConn;

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
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import vn.whoever.models.dao.ConnDB;

/**
 * Created by spider man on 1/7/2016.
 */
public class NewsTrans {

    private Integer httpStatusCode = null;
    private Activity activity;
    private String url_news = "http://192.168.1.112:8080/mainserver/mobile/news";

    private NewsTrans() {}

    public NewsTrans(Activity activity) {
        this.activity = activity;
    }

    public void getNewsFeed(String order, final int offset) {
        Map<String, Object> mapGetStatus = new LinkedHashMap<>();
        mapGetStatus.put("order", order);
        mapGetStatus.put("offset", offset);

        JSONObject jsonReqStatus = new JSONObject(mapGetStatus);

        JsonArrayRequest newsRequest = new JsonArrayRequest(Request.Method.POST, url_news, jsonReqStatus,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                Log.d("responseStatus", resp.toString());
                // TODO: insert DB status
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                ContentValues values = new ContentValues();
                for(int i = 0; i < resp.length(); ++i) {
                    try {
                        JSONObject obj = resp.getJSONObject(i);
                        values.put("idStatus", obj.getString("idStatus"));
                        values.put("ssoIdPoster", obj.getString("ssoIdPoster"));
                        values.put("namePoster", obj.getString("namePoster"));
                        values.put("timePost", obj.getString("timePost"));
                        values.put("contentText", obj.getString("contentText"));
                        values.put("contentImage", obj.getString("contentImage"));
                        values.put("totalLike", obj.getInt("totalLike"));
                        values.put("totalDislike", obj.getInt("totalDislike"));
                        values.put("totalComment", obj.getInt("totalComment"));
                        values.put("interact", obj.getString("interact"));
                        db.insert("News", null, values);
                    } catch (JSONException e) {
                        e.printStackTrace();
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
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
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

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(newsRequest);
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
