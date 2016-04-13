package vn.whoever.transactionconn;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.whoever.transactionconn.request.GsonRequest;

/**
 * Created by spider man on 1/7/2016.
 */
public class BeginTransaction {

    private static BeginTransaction transaction = new BeginTransaction();
    private static Activity myActivity;

    // LocalAccount user;
    private Integer httpStatusCode = null;

    public static BeginTransaction getTransaction(Activity acctivity) {
        myActivity = acctivity;
        return transaction;
    }

    public void getRequestLogin(final String ssoId, final String password) {

        Map<String, String> jsonLogin = new HashMap<>();
        jsonLogin.put("ssoId", ssoId);
        jsonLogin.put("password", password);

        GsonRequest<Object> requestLogin = new GsonRequest(Request.Method.GET,
                AddressConn.url_login,
                String.class,
                jsonLogin,
                new Response.Listener<Object>() {
                    @Override
                    public void onResponse(Object response) {
                        try {
                            Log.d("Json Response", "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    };
                },
                new Response.ErrorListener() {
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

        TransactionQueue.getsInstance(myActivity).addToRequestQueue(requestLogin);
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
    }

    public boolean registerUser(String ssoId, String password, String nickName, String birthday, String langCode) {

        Map<String, String> jsonRegister = new HashMap<>();
        jsonRegister.put("ssoId", ssoId);
        jsonRegister.put("password", password);
        jsonRegister.put("nickName", nickName);
        jsonRegister.put("birthday", birthday);
        jsonRegister.put("langCode", langCode);

        JsonObjectRequest registerRequest = new JsonObjectRequest(Request.Method.POST, AddressConn.url_register,
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

    public void getRequestLoginAnonymous(String langCode) {
        httpStatusCode = null;
        UrlQuery query = new UrlQuery(AddressConn.url_login_anonymous);
        query.putVariable(langCode);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, query.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ssoId", response);
                /**
                 * TODO: need stored ssoId into database
                 *
                 */
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
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
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(myActivity).addToRequestQueue(stringRequest);
    }

    public void createNewAccount(String name, String password, String birthday, String language) {

        String url = "";

    }

    public void getTermUser() {
        String urlQuery = AddressConn.URL_USER + "/term";

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

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

}
