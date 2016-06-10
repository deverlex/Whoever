package vn.whoever.TransConnection;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import vn.whoever.models.SearchContact;
import vn.whoever.models.dao.ConnDB;

/**
 * Created by Nguyen Van Do on 4/23/2016.
 * Class implement connection and transaction contact info
 */
public class ContactTransaction extends AbstractTransaction {

    private List<SearchContact> searchContactList;

    public ContactTransaction(Activity activity) {
        super(activity);
    }

    public List<SearchContact> getSearchContactList() {
        return this.searchContactList;
    }

    /**
     * GET search contact on: /mobille/friends/search/{query}
     */
    public void queryContact(String query) {
        searchContactList = new ArrayList<SearchContact>();
        String url_query = address + "/friends/search";
        UrlQuery urlQuery = new UrlQuery(url_query);
        urlQuery.putPathVariable(query);

        JsonArrayRequest queryContactRequest = new JsonArrayRequest(Request.Method.GET, urlQuery.getUrl(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray resp) {
                        try {
                            for (int i = 0; i < resp.length(); ++i) {
                                JSONObject obj = resp.getJSONObject(i);
                                SearchContact contact = new SearchContact();
                                contact.setNickName(obj.getString("nickName"));
                                contact.setAvatar("null");
                                contact.setSsoId(obj.getString("ssoId"));
                                contact.setIsFriend(obj.getBoolean("isFriend"));
                                searchContactList.add(contact);
                            }
                            Log.d("size of query:", String.valueOf(resp.length()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

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

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(queryContactRequest, "queryContact");
    }

    /***
     * GET list friend on: /mobile/friends
     */
    public void getListFriends() {
        String url_getFriends = address + "/friends/";

        JsonArrayRequest requestGetFriends = new JsonArrayRequest(Request.Method.GET, url_getFriends,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray resp) {
                        SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                        for (int i = 0; i < resp.length(); ++i) {
                            try {
                                JSONObject obj = resp.getJSONObject(i);
                                ContentValues values = new ContentValues();
                                values.put("ssoId", obj.getString("ssoId"));
                                values.put("nickName", obj.getString("nickName"));
                                values.put("latestOnline", obj.getString("latestOnline"));
                                db.insert("Contact", null, values);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        httpStatusCode = HttpStatus.SC_CREATED;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Extraction error response
                exTractError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // SET header HTTP packet
                return onCreateHeaders(super.getHeaders());
            }

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(requestGetFriends, "getListFriends");
    }

    /**
     * POST following contact on: /mobile/friends/{ssoId}
     */
    public void followContact(String ssoId) {
        String url_follow = address + "/friends";
        UrlQuery urlQuery = new UrlQuery(url_follow);
        urlQuery.putPathVariable(ssoId);

        StringRequest followRequest = new StringRequest(Request.Method.POST, urlQuery.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                httpStatusCode = HttpStatus.SC_OK;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exTractError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Set header for request
                return onCreateHeaders(super.getHeaders());
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(followRequest, "followRequest");
    }
}
