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
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.whoever.models.dao.ConnDB;

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
            public void onResponse(JSONObject res) {
                try {
                    String avatarPhoto = res.getString("avatarPhoto");
                    String coverPhoto = res.getString("coverPhoto");
                    String nickName = res.getString("nickName");
                    String langName = res.getString("langName");
                    String birthday = res.getString("birthday");
                    String gender = res.getString("gender");
                    String mobile = res.getString("mobile");
                    String email = res.getString("email");
                    String privacy = res.getString("privacy");
                    Boolean isOnline = res.getBoolean("isOnline");

                    ConnDB.getConn().openDataBase();
                    SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("avatarPhoto", avatarPhoto);
                    values.put("coverPhoto", coverPhoto);
                    values.put("nickName", nickName);
                    values.put("langName", langName);
                    values.put("birthday", birthday);
                    values.put("gender", gender);
                    values.put("mobile", mobile);
                    values.put("email", email);
                    values.put("privacy", privacy);
                    values.put("isOnline", isOnline);
                    db.execSQL("delete from LocalProfile");
                    db.insert("LocalProfile", null, values);
                    ConnDB.getConn().close();
                } catch (Exception e) {
                    e.printStackTrace();
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
                Map<String, String> headers = response.headers;
                ConnDB.getConn().openDataBase();
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("token", headers.get("Whoever-token"));
                values.put("expTime", headers.get("Token-expiration"));
                db.execSQL("delete from Auth");
                db.insert("Auth", null, values);
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
