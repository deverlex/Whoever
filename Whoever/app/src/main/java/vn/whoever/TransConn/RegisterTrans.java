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
import java.util.LinkedHashMap;
import java.util.Map;

import vn.whoever.models.dao.ConnDB;

/**
 * Created by spider man on 4/21/2016.
 */
public class RegisterTrans {

    private Activity activity;
    private Integer httpStatusCode = null;
    private String url_register = "http://192.168.1.112:8080/mainserver/mobile/register";

    public RegisterTrans(Activity activity) {
        this.activity = activity;
    }

    public void registerUser(String ssoId, String password, String nickName, String birthday, String langCode) {
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
                        db.close();
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
                db.close();
                return super.parseNetworkResponse(response);
            }

        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(registerRequest);
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
