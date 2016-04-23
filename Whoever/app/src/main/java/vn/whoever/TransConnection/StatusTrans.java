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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import vn.whoever.models.dao.ConnDB;

/**
 * Created by spider man on 4/22/2016.
 */
public class StatusTrans {

    private Activity activity;
    private Integer httpStatusCode = null;
    private boolean isInsert;

    public StatusTrans(Activity activity) {
        this.activity = activity;
    }

    public void postStatus(String contentText, String contentImage, String privacy, String isUseAccount) {
        String url_post_status = "http://192.168.1.112:8080/mainserver/mobile/status";
        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("contentText", contentText);
        parameter.put("contentImage", contentImage);
        parameter.put("privacy", privacy);
        parameter.put("isUseAccount", isUseAccount);

        JsonObjectRequest requestPostStatus = new JsonObjectRequest(Request.Method.POST, url_post_status,
                new JSONObject(parameter), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

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

            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(requestPostStatus, "postStatus");
    }

    public void getNewsFeed(String order, final int offset) {
        String url_news = "http://192.168.1.112:8080/mainserver/mobile/news";
        Map<String, Object> mapGetStatus = new LinkedHashMap<>();
        mapGetStatus.put("order", order);
        mapGetStatus.put("offset", offset);
        JSONObject jsonReqStatus = new JSONObject(mapGetStatus);
        isInsert = true;

        JsonArrayRequest newsRequest = new JsonArrayRequest(Request.Method.POST, url_news, jsonReqStatus,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray resp) {
                        // TODO: insert DB status
                        SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                        ContentValues values = new ContentValues();
                        for(int i = 0; i < resp.length() && isInsert; ++i) {
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
                                Log.d("insertStatus", "error insert!!");
                            }
                            values.clear();
                        }
                        isInsert = false;
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
                return onCreateHeaders();
            }

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(newsRequest, "requestNews");
    }

    public void interactStatus(String type, String idStatus, String idComment) {
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

    public void getHomePage() {
        /**
         * Dua vao id nguoi dung ma lay langCode
         */

    }

    public Map<String, String> onCreateHeaders() {
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
