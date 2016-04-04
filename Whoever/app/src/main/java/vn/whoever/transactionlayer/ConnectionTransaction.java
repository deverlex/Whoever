package vn.whoever.transactionlayer;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.whoever.models.LocalAccount;
import vn.whoever.utils.LoginState;

/**
 * Created by spider man on 1/7/2016.
 */
public class ConnectionTransaction {

    private static ConnectionTransaction transaction = new ConnectionTransaction();
    private static Activity myActivity;
    private static View myView;

    private int fState = LoginState.FAIL;

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
////                        fState = LoginState.PASS;
////                    } else {
////                        fState = LoginState.WELCOME;
////                    }
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                    fState = LoginState.FAIL;
////
////                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("RESPONSE: ", error.toString());
//                fState = LoginState.FAIL;
//            }
//        });
//        TransactionQueue.getsInstance(myActivity).addToRequestQueue(objectRequest);

        return fState;
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

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
     * TODO: using POST method upload IMEI mobile device
     */
    public void getRequestLoginAnonymous(String langCode, String birthday) {

        UrlQuery query = new UrlQuery(AddressTransaction.url_login_with_anonymous);
        query.putParam("langCode", langCode);
        query.putParam("birthday", birthday);

        JsonObjectRequest loginAnonymousRequest = new JsonObjectRequest(Request.Method.GET, query.getUrl(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

//        HashMap<String, String> params = new HashMap<>();
//        params.put("imei", serialUser);
//
//        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
//                new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                // kết quả trả về khi đẩy lên server
//
//                Log.d("ANONYMOUS LOGIN: ", response.toString());
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("ANONYMOUS LOGIN: ", "ERROR");
//            }
//        });
//        TransactionQueue.getsInstance(myActivity).addToRequestQueue(objectRequest);
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
