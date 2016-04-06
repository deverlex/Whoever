package vn.whoever.transactionlayer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.whoever.models.LocalAccount;
import vn.whoever.transactionlayer.request.GsonRequest;

/**
 * Created by spider man on 1/7/2016.
 */
public class ConnectionTransaction {

    private static ConnectionTransaction transaction = new ConnectionTransaction();
    private static Activity myActivity;
    private static View myView;


    private LocalAccount user;

    public static ConnectionTransaction getInstance(Activity acctivity, View view) {
        myActivity = acctivity;
        myView = view;
        return transaction;
    }

    public final int getRequestLogin(final String ssoId, final String password) {

        Map<String, String> jsonLogin = new HashMap<>();
        jsonLogin.put("ssoId", ssoId);
        jsonLogin.put("password", password);

        GsonRequest<Object> gsonRequest = new GsonRequest(Request.Method.GET,
                "",
                null,
                createMyReqSuccessListener(),
                createMyReqErrorListener()) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("user-agent", "some_bitch_ass_header");

                return headers;
            }
        };


//        JsonObjectRequest objectRequest = new JsonObjectRequest( Request.Method.GET ,urlQuery.getUrl(),
//                new Response.Listener<JSONObject>(){
//            @Override
//            public void onResponse(JSONObject response) {
//
////                    if(true) {
////                        /**
////                         * TODO: Check response from server
////                         *
////                         */
////                        fState = ResponseState.PASS;
////                    } else {
////                        fState = ResponseState.WELCOME;
////                    }
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                    fState = ResponseState.FAIL;
////
////                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("RESPONSE: ", error.toString());
//                fState = ResponseState.FAIL;
//            }
//        });
//        TransactionQueue.getsInstance(myActivity).addToRequestQueue(objectRequest);

        return 2;
    }

    public boolean registerUser(String ssoId, String password, String nickName, String birthday, String langCode) {

        Map<String, String> jsonRegister = new HashMap<>();
        jsonRegister.put("ssoId", ssoId);
        jsonRegister.put("password", password);
        jsonRegister.put("nickName", nickName);
        jsonRegister.put("birthday", birthday);
        jsonRegister.put("langCode", langCode);

        JsonObjectRequest registerRequest = new JsonObjectRequest(Request.Method.POST, AddressTransaction.url_register,
                new JSONObject(jsonRegister),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /**
                         * Response status on home page
                         */
                        Log.d("Response from server", response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response from server", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }


        };
        TransactionQueue.getsInstance(myActivity).addToRequestQueue(registerRequest);
        return true;
    }

    /**
     * @param langCode, birthday
     *
     * TODO: using GET method
     */

    public void getRequestLoginAnonymous(String langCode, String birthday) {

        UrlQuery query = new UrlQuery(AddressTransaction.url_login_with_anonymous);
        query.putParam("langCode", langCode);
        query.putParam("birthday", birthday);

        Log.d("URL login anonymous", query.getUrl());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, query.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Request login anonymous", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Request login anonymous", error.toString());
            }
        }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.d("header response", response.headers.toString());
                return super.parseNetworkResponse(response);
            }
        };

        TransactionQueue.getsInstance(myActivity).addToRequestQueue(stringRequest);
    }

    private Response.Listener<Object> createMyReqSuccessListener() {
        return new Response.Listener<Object>() {
            @Override
            public void onResponse(Object response) {
                try {
                    Log.d("Json Response", "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
    }

    public void createNewAccount(String name, String password, String birthday, String language) {

        String url = "";

    }

    public void getTermUser() {
        String urlQuery = AddressTransaction.URL_USER + "/term";

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, urlQuery, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        TransactionQueue.getsInstance(myActivity).addToRequestQueue(objectRequest);
    }

}
