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
 * Created by Nguyen Van Do on 4/22/2016.
 * Class implement connection and transaction user's account
 */
public class InfoTransaction extends AbstractTransaction {

    public InfoTransaction(Activity activity) {
        super(activity);
    }

    /**
     * GET reconnect on: /mobile/users/reconnect
     */
    public void getReConnect() {
        String url_reconnect = address + "/users/reconnect";

        StringRequest reConnectRequest = new StringRequest(Request.Method.GET, url_reconnect, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // TODO: nothing
                httpStatusCode = Integer.valueOf(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Extraction an error
                exTractError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // SET header -> set token on header -> server check token -> accepted/unaccepted
                return onCreateHeaders(super.getHeaders());
            }

            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(reConnectRequest, "reConnect");
    }

    /**
     * POST request login to server on: /mobile/users/login
     * JSON string:
     * {
     *     "ssoId" : "",
     *     "password" : ""
     * }
     */
    public void getRequestLogin(final String ssoId, final String password) {
        String url_login = address + "/users/login";
        httpStatusCode = null;

        Map<String, String> jsonLogin = new HashMap<>();
        jsonLogin.put("ssoId", ssoId);
        jsonLogin.put("password", password);

        final JsonObjectRequest requestLogin = new JsonObjectRequest(Request.Method.POST, url_login, new JSONObject(jsonLogin) ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                try {
                    SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("id", 1);
                    values.put("avatarPhoto", res.getString("avatarPhoto"));
                    values.put("coverPhoto", res.getString("coverPhoto"));
                    values.put("nickName", res.getString("nickName"));
                    values.put("langName", res.getString("langName"));
                    values.put("birthday", res.getString("birthday"));
                    values.put("gender", res.getString("gender"));
                    values.put("mobile", res.getString("mobile"));
                    values.put("email", res.getString("email"));
                    values.put("privacy", res.getString("privacy"));
                    values.put("isOnline", res.getBoolean("isOnline"));
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
                /**
                 * Insert token info into database after request login
                 */
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

    /***
     * POST register user on: /mobile/users/register
     * JSON string:
     * {
     *      "ssoId" : "",
     *      "password" : "",
     *      "nickName" : "",
     *      "birthday" : "",
     *      "langCode" : ""
     * }
     */
    public void registerUser(String ssoId, String password, String nickName, String birthday, String langCode) {
        String url_register = address + "/users/register";
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
                //Insert token info into database after register account on server
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

    /**
     * GET request login with anonymous mode on: /mobile/users/anonymous/{langCode}
     */
    public void getRequestLoginAnonymous(String langCode) {
        String url_anonymous = address + "/users/anonymous";
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
                db.execSQL("delete from LocalProfile");
                db.insert("LocalProfile", null, values);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Extraction an error on response
                exTractError(error);
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                Map<String, String> headers = response.headers;
                // Insert token info into database
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("token", headers.get("Whoever-token"));
                values.put("expTime", headers.get("Token-expiration"));
                db.execSQL("delete from Auth");
                db.insert("Auth", null, values);
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(stringRequest);
    }

    /**
     * GET logout server on: /mobile/users/logout
     */
    public void logout() {
        String url_anonymous = address + "/users/logout";
        StringRequest logoutRequest = new StringRequest(Request.Method.GET, url_anonymous, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return onCreateHeaders(super.getHeaders());
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(logoutRequest, "logout");
    }
}