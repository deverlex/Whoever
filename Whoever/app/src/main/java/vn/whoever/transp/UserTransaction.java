package vn.whoever.transp;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import vn.whoever.MainActivity;
import vn.whoever.models.User;

/**
 * Created by spider man on 1/7/2016.
 */
public class UserTransaction {

    private static UserTransaction transaction = new UserTransaction();
    private static Activity myActivity;
    private static View myView;
    private boolean isOke = false;

    private User user;

    private UserTransaction() {}

    public static UserTransaction getInstance(Activity acctivity, View view) {
        myActivity = acctivity;
        myView = view;
        return transaction;
    }

    public boolean getRequestLogin(final String email, final String password) {

        //String url = AddressTrans.URL_USER + "/login"; //?email="+email+"&password="+password;
        UrlQuery urlQuery = new UrlQuery(AddressTrans.URL_USER + "/login");
        urlQuery.putParam("email", email);
        urlQuery.putParam("password", password);

        JsonObjectRequest objectRequest = new JsonObjectRequest( Request.Method.GET ,urlQuery.getUrl(),
                new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    user = new User();
                //    user.setId(response.getInt("id"));
                    user.setEmail(response.getString("name"));
                //    user.setNickName(response.getString("nickName"));
                    user.setPassword(response.getString("password"));

                 //   Log.d("nickName: ", user.getNickName());
                    Log.d("email: ", user.getEmail());
                    Log.d("password: ", user.getPassword());


                    isOke = true;

                } catch (JSONException e) {
                    e.printStackTrace();
                    isOke = false;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RESPONSE: ", error.toString());
                isOke = false;
            }
        });
        ApplicationController.getsInstance(myActivity).addToRequestQueue(objectRequest);

        return isOke;
    }

    /**
     * @param serialUser
     *
     * TODO: using POST method upload IMEI mobile device
     */
    public void getRequestLoginAnonymous(String serialUser) {
        String url = AddressTrans.URL_USER + "/login-anonymous";

        HashMap<String, String> params = new HashMap<>();
        params.put("imei", serialUser);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // kết quả trả về khi đẩy lên server

                Log.d("ANONYMOUS LOGIN: ", response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ANONYMOUS LOGIN: ", "ERROR");
            }
        });
        ApplicationController.getsInstance(myActivity).addToRequestQueue(objectRequest);
    }

    public void createNewAccount(String name, String password, String birthday, String language) {

        String url = "";

    }

    public void getTermUser() {
        String urlQuery = AddressTrans.URL_USER + "/term";

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, urlQuery, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        ApplicationController.getsInstance(myActivity).addToRequestQueue(objectRequest);
    }

}
