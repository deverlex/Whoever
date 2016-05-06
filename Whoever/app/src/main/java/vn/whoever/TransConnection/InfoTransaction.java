package vn.whoever.TransConnection;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
public class InfoTransaction extends AbstractTransaction {

    public InfoTransaction(Activity activity) {
        super(activity);
    }

    public void getReConnect() {
        String url_reconnect = "http://192.168.1.112:8080/mainserver/mobile/users/reconnect";

        StringRequest reConnectRequest = new StringRequest(Request.Method.GET, url_reconnect, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // TODO: nothing
                httpStatusCode = Integer.valueOf(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exTractError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return onCreateHeaders(super.getHeaders());
            }

            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(reConnectRequest, "reConnect");
    }

    public void getRequestLogin(final String ssoId, final String password) {
        String url_login = "http://192.168.1.112:8080/mainserver/mobile/users/login";
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

                    SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("id", 1);
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

    public void registerUser(String ssoId, String password, String nickName, String birthday, String langCode) {
        String url_register = "http://192.168.1.112:8080/mainserver/mobile/users/register";
        Map<String, Object> jsonRegister = new LinkedHashMap<>();
        jsonRegister.put("ssoId", ssoId);
        jsonRegister.put("password", password);
        jsonRegister.put("nickName", nickName);
        jsonRegister.put("birthday", birthday);
        jsonRegister.put("langCode", langCode);

        JsonObjectRequest registerRequest = new JsonObjectRequest(Request.Method.POST, url_register,
                new JSONObject(jsonRegister),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("langName", response.toString());
                        db.update("LocalProfile", values, "id = 1", null);
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
                Map<String, String> headers = response.headers;
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("token", headers.get("Whoever-token"));
                values.put("expTime", headers.get("Token-expiration"));
                db.execSQL("delete from Auth");
                db.insert("Auth", null, values);
                return super.parseNetworkResponse(response);
            }

        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(registerRequest);
    }

    public void getRequestLoginAnonymous(String langCode) {
        String url_anonymous = "http://192.168.1.112:8080/mainserver/mobile/users/anonymous";
        httpStatusCode = null;
        UrlQuery query = new UrlQuery(url_anonymous);
        query.putPathVariable(langCode);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, query.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String res) {
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("id", 1);
                values.put("langName", res);
                Log.d("language", res);
                db.execSQL("delete from LocalProfile");
                db.insert("LocalProfile", null, values);
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
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("token", headers.get("Whoever-token"));
                values.put("expTime", headers.get("Token-expiration"));
                Log.d("token", headers.get("Whoever-token"));
                db.execSQL("delete from Auth");
                db.insert("Auth", null, values);
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(stringRequest);
    }

    public void logout() {
        String url_anonymous = "http://192.168.1.112:8080/mainserver/mobile/users/logout";

        StringRequest logoutRequest = new StringRequest(Request.Method.GET, url_anonymous, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
    }
}
