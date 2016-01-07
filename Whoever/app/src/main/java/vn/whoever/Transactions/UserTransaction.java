package vn.whoever.Transactions;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import vn.whoever.models.User;

/**
 * Created by spider man on 1/7/2016.
 */
public class UserTransaction {

    private static UserTransaction transaction = new UserTransaction();
    private User user;

    private UserTransaction() {}

    public static UserTransaction getInstance() {
        return transaction;
    }

    public User getRequestLogin(Context context, String email, String password) {

        String url = AddressTrans.URL_USER + "/login?email="+email+"&password="+password;
        JsonObjectRequest objectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    user = new User();
                    //Log.d("Response:%n %s", response.toString(4));
                    user.setId(response.getInt("id"));
                    user.setEmail(response.getString("email"));
                    user.setNickName(response.getString("nickName"));
                    user.setPassword(response.getString("password"));

                    Log.d("nickName: ", user.getNickName());
                    Log.d("email: ", user.getEmail());
                    Log.d("password: ", user.getPassword());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.e("Error: ", error.getMessage());
                user = null;
                Log.d("RESPONSE: ", "NOT RESPONSE ERROR 404!");
            }
        });
        ApplicationController.getsInstance(context).addToRequestQueue(objectRequest);
        return user;
    }
}
