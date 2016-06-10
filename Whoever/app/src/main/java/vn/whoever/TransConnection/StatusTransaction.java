package vn.whoever.TransConnection;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import vn.whoever.models.Status;
import vn.whoever.models.dao.ConnDB;

/**
 * Created by Nguyen Van Do on 4/22/2016.
 * Class implement connection and transaction status info
 */
public class StatusTransaction extends AbstractTransaction {

    private boolean isInsert;

    public StatusTransaction(Activity activity) {
        super(activity);
    }

    /**
     * POST post status on: /mobile/status
     * JSON string:
     * {
     *      "contentText" : "",
     *      "contentImage" : "",
     *      "privacy" : "",
     *      "isUseAccount" : ""
     * }
     */
    public void postStatus(String contentText, String contentImage, String privacy, String isUseAccount) {
        String url_post_status = address + "/status";
        Map<String, String> parameter = new LinkedHashMap<>();
        parameter.put("contentText", contentText);
        parameter.put("contentImage", contentImage);
        parameter.put("privacy", privacy);
        parameter.put("isUseAccount", isUseAccount);

        JsonObjectRequest requestPostStatus = new JsonObjectRequest(Method.POST, url_post_status,
                new JSONObject(parameter), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                httpStatusCode = HttpStatus.SC_CREATED;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Extraction error from response
                exTractError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // SET header for HTTP packet
                return onCreateHeaders(super.getHeaders());
            }

            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(requestPostStatus, "postStatus");
    }

    /**
     * POST get news feed on: /mobile/news
     * JSON string:
     * {
     *      "order" : "",
     *      "offset"  : ""
     * }
     */
    public void getNewsFeed(String order, final int offset) {
        String url_news = address + "/news";
        Map<String, Object> mapGetStatus = new LinkedHashMap<>();
        mapGetStatus.put("order", order);
        mapGetStatus.put("offset", offset);

        JSONObject jsonReqStatus = new JSONObject(mapGetStatus);
        isInsert = true;

        JsonArrayRequest newsRequest = new JsonArrayRequest(Method.POST, url_news, jsonReqStatus,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray resp) {
                        // TODO: insert DB status
                        Log.d("responseStatus", resp.toString());
                        SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                        ContentValues values = new ContentValues();
                        for(int i = 0; i < resp.length() && isInsert; ++i) {
                            try {
                                JSONObject obj = resp.getJSONObject(i);
                                values.put("idStatus", obj.getString("idStatus"));
                                values.put("ssoIdPoster", obj.getString("ssoIdPoster"));
                                values.put("namePoster", obj.getString("namePoster"));
                                values.put("timePost", obj.getString("timePost"));
                                values.put("contentText", obj.getString("contentText"));
                                values.put("contentImage", obj.getString("contentImage"));
                                values.put("totalLike", obj.getInt("totalLike"));
                                values.put("totalDislike", obj.getInt("totalDislike"));
                                values.put("totalComment", obj.getInt("totalComment"));
                                values.put("interact", obj.getString("interact"));
                                values.put("isHome", false);
                                db.insert("Status", null, values);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            values.clear();
                        }
                        isInsert = false;
                        httpStatusCode = HttpStatus.SC_CREATED;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Extraction error from response
                exTractError(error);
            }
        }){
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
        TransactionQueue.getsInstance(activity).addToRequestQueue(newsRequest, "requestNews");
    }

    /**
     * PUT status's interaction on: /mobile/status/{idStatus}
     * JSON string:
     * { "interact" : "" }
     */
    public void interactStatus(String interact, String idStatus) {
        String url_interact = address + "/status";
        UrlQuery urlQuery = new UrlQuery(url_interact);
        urlQuery.putPathVariable(idStatus);

        Map<String, String> interactMap = new LinkedHashMap<>();
        interactMap.put("interact", interact);

        JsonObjectRequest interactRequest = new JsonObjectRequest(Method.PUT, urlQuery.getUrl(),
                new JSONObject(interactMap), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Nothing
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Extraction error from response
                exTractError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // SET header HTTP packet
                return onCreateHeaders(super.getHeaders());
            }

            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(interactRequest, "requestInteractStatus");
    }
}
