package vn.whoever.TransConnection;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import vn.whoever.models.Comment;
import vn.whoever.models.dao.ConnDB;

/**
 * Created by spider man on 4/22/2016.
 */
public class CommentTransaction extends AbstractTransaction {

    private List<Comment> commentList = commentList = new ArrayList<Comment>();;

    public CommentTransaction(Activity activity) {
        super(activity);
    }

    public List<Comment> getCommentList () {
        return commentList;
    }

    public void getCommentOfStatus(final String idStatus) {
        commentList.clear();
        String url_get_comment = "http://192.168.1.112:8080/mainserver/mobile/status";
        UrlQuery urlQuery = new UrlQuery(url_get_comment);
        urlQuery.putPathVariable(idStatus);
        urlQuery.putPathVariable("comments");

        Log.d("urlGetComment", urlQuery.getUrl());

        JsonArrayRequest requestComment = new JsonArrayRequest(Request.Method.GET, urlQuery.getUrl(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray resp) {
                Log.d("lengthCommentArr", String.valueOf(resp.length()));
                for(int i = 0; i < resp.length(); ++i) {
                    try {
                        JSONObject obj = resp.getJSONObject(i);
                        Comment comment = new Comment();
                        comment.setIdStatus(idStatus);
                        comment.setIdComment(obj.getString("idComment"));
                        comment.setSsoIdPoster(obj.getString("ssoIdPoster"));
                        comment.setNamePoster(obj.getString("namePoster"));
                        comment.setAvatarPoster(obj.getString("avatarPoster"));
                        comment.setContent(obj.getString("content"));
                        comment.setTimePost(obj.getString("timePost"));
                        comment.setTotalLike(obj.getInt("totalLike"));
                        comment.setTotalDislike(obj.getInt("totalDislike"));
                        comment.setInteract(obj.getString("interact"));
                        commentList.add(comment);
                    } catch (JSONException e) {
                        Log.d("insertComment", "error insert!!!");
                    }
                    httpStatusCode = HttpStatus.SC_CREATED;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                exTractError(error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                return onCreateHeaders(super.getHeaders());
            }

            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(requestComment, "requestComment");
    }

    public void postCommentForStatus(String idStatus, String content, Boolean isUseAccount) {
        String url_post_cmt = "http://192.168.1.112:8080/mainserver/mobile/status";
        UrlQuery urlQuery = new UrlQuery(url_post_cmt);
        urlQuery.putPathVariable(idStatus);
        urlQuery.putPathVariable("comments");
        Map<String, String> mapReq = new LinkedHashMap<>();
        mapReq.put("content", content);
        mapReq.put("isUseAccount", String.valueOf(isUseAccount));

        JsonObjectRequest postCmtRequest = new JsonObjectRequest(Request.Method.POST, urlQuery.getUrl(), new JSONObject(mapReq), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
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

            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                httpStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        TransactionQueue.getsInstance(activity).addToRequestQueue(postCmtRequest, "postComment");
    }

    public void interactComment(String type, String idStatus, String idComment) {
        String url_interact = "http://192.168.1.112:8080/mainserver/mobile/status";
        UrlQuery urlQuery = new UrlQuery(url_interact);
        urlQuery.putPathVariable(idStatus);
        StringRequest requestInteract = new StringRequest(Request.Method.GET, urlQuery.getUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //nothing
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
        TransactionQueue.getsInstance(activity).addToRequestQueue(requestInteract, "requestInteractStatus");
    }
}
